package com.example.foosballapp.gameresults

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.example.foosballapp.model.GameResult
import com.example.foosballapp.databinding.FragmentGameResultsBinding
import com.example.foosballapp.gameresults.GameResultDetailsDialogFragment.Companion.ARG_GAME_RESULT
import com.example.foosballapp.gameresults.GameResultDetailsDialogFragment.Companion.ARG_IS_NEW_RESULT
import com.example.foosballapp.gameresults.GameResultDetailsDialogFragment.Companion.KEY_GAME_RESULT_DETAILS
import com.example.foosballapp.gameresults.recyclerview.GameResultsAdapter
import com.example.foosballapp.util.disposeOnDestroy
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GameResultsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: GameResultsViewModel

    private val adapter = GameResultsAdapter(::onGameResultClicked)
    private lateinit var binding: FragmentGameResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(KEY_GAME_RESULT_DETAILS) { _, bundle ->
            val resultArg = if (Build.VERSION.SDK_INT >= 33) {
                bundle.getParcelable(
                    ARG_GAME_RESULT,
                    GameResultDetailsDialogFragment.ResultArg::class.java
                )
            } else {
                @Suppress("DEPRECATION")
                bundle.getParcelable(ARG_GAME_RESULT)
            }
            resultArg?.let {
                if (bundle.getBoolean(ARG_IS_NEW_RESULT)) {
                    viewModel.saveGameResult(it.toGameResult())
                } else {
                    viewModel.updateGameResult(it.toGameResult())
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            GameResultDetailsDialogFragment.newInstance().show(
                requireActivity().supportFragmentManager,
                GameResultDetailsDialogFragment::class.simpleName
            )
        }

        viewModel.gameResults
            .subscribe(adapter::submitList)
            .disposeOnDestroy(viewLifecycleOwner)
    }

    private fun onGameResultClicked(gameResult: GameResult) {
        val resultArg = GameResultDetailsDialogFragment.ResultArg.fromGameResult(gameResult)
        GameResultDetailsDialogFragment.newInstance(resultArg)
            .show(
                requireActivity().supportFragmentManager,
                GameResultDetailsDialogFragment::class.simpleName
            )
    }
}