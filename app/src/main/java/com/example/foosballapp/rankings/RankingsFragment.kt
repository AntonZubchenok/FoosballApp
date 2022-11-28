package com.example.foosballapp.rankings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foosballapp.databinding.FragmentRankingsBinding
import com.example.foosballapp.rankings.RankingsViewModel.SortType.GAMES
import com.example.foosballapp.rankings.RankingsViewModel.SortType.WINS
import com.example.foosballapp.rankings.recyclerview.RankingsAdapter
import com.example.foosballapp.util.disposeOnDestroy
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RankingsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: RankingsViewModel

    private val adapter = RankingsAdapter()
    private lateinit var binding: FragmentRankingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRankingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        binding.radioGames.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.setSortType(GAMES)
            }
        }

        binding.radioWins.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.setSortType(WINS)
            }
        }

        viewModel.sortType
            .subscribe {
                when (it) {
                    GAMES -> binding.radioGames.isChecked = true
                    WINS -> binding.radioWins.isChecked = true
                }
            }
            .disposeOnDestroy(viewLifecycleOwner)

        viewModel.players
            .subscribe(adapter::submitList)
            .disposeOnDestroy(viewLifecycleOwner)
    }
}