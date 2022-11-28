package com.example.foosballapp.gameresults

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.foosballapp.R
import com.example.foosballapp.model.GameResult
import com.example.foosballapp.model.Score
import com.example.foosballapp.databinding.FragmentGameResultDetailsBinding
import com.example.foosballapp.util.autoCleared
import kotlinx.parcelize.Parcelize

class GameResultDetailsDialogFragment : DialogFragment(R.layout.fragment_game_result_details) {

    companion object {
        const val KEY_GAME_RESULT_DETAILS = "KEY_GAME_RESULT_DETAILS"
        const val ARG_GAME_RESULT = "ARG_GAME_RESULT"
        const val ARG_IS_NEW_RESULT = "ARG_IS_NEW_RESULT"

        fun newInstance(result: ResultArg? = null) = GameResultDetailsDialogFragment().apply {
            arguments = bundleOf(ARG_GAME_RESULT to result)
        }
    }

    private var binding: FragmentGameResultDetailsBinding by autoCleared()
    private val resultArg by lazy {
        if (Build.VERSION.SDK_INT >= 33) {
            arguments?.getParcelable(ARG_GAME_RESULT, ResultArg::class.java)
        } else {
            @Suppress("DEPRECATION")
            (arguments?.getParcelable(ARG_GAME_RESULT) as? ResultArg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            com.google.android.material.R.style.Theme_MaterialComponents_Light_Dialog_MinWidth
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameResultDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultArg?.let {
            binding.firstName.setText(it.firstName)
            binding.firstGoals.setText(it.firstGoals.toString())
            binding.secondName.setText(it.secondName)
            binding.secondGoals.setText(it.secondGoals.toString())
        }

        binding.save.setOnClickListener {
            val firstName = binding.firstName.text.toString().trim()
            val firstGoals = binding.firstGoals.text.toString().trim().toIntOrNull()
            val secondName = binding.secondName.text.toString().trim()
            val secondGoals = binding.secondGoals.text.toString().trim().toIntOrNull()

            if (firstName.isNotEmpty() && firstGoals != null && secondName.isNotEmpty() && secondGoals != null) {
                val result =
                    ResultArg(resultArg?.id ?: -1, firstName, firstGoals, secondName, secondGoals)
                setFragmentResult(
                    KEY_GAME_RESULT_DETAILS,
                    bundleOf(
                        ARG_GAME_RESULT to result,
                        ARG_IS_NEW_RESULT to (resultArg?.id == null)
                    )
                )
            }
            dismiss()
        }
    }

    @Parcelize
    data class ResultArg(
        val id: Int,
        val firstName: String,
        val firstGoals: Int,
        val secondName: String,
        val secondGoals: Int
    ) : Parcelable {

        companion object {

            fun fromGameResult(result: GameResult) = ResultArg(
                id = result.id,
                firstName = result.scores.first.name,
                firstGoals = result.scores.first.goals,
                secondName = result.scores.second.name,
                secondGoals = result.scores.second.goals,
            )
        }

        fun toGameResult(): GameResult {
            return GameResult(id, Score(firstName, firstGoals) to Score(secondName, secondGoals))
        }
    }
}