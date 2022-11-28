package com.example.foosballapp.gameresults.recyclerview

import android.view.ViewGroup
import com.example.foosballapp.model.GameResult
import com.example.foosballapp.databinding.ItemGameResultBinding
import com.example.foosballapp.util.ViewBindingViewHolder

class GameResultViewHolder(
    parent: ViewGroup,
    private val onClick: (GameResult) -> Unit
) : ViewBindingViewHolder<ItemGameResultBinding>(parent, ItemGameResultBinding::inflate) {

    private lateinit var result: GameResult

    init {
        itemView.setOnClickListener { onClick(result) }
    }

    fun bind(result: GameResult) {
        this@GameResultViewHolder.result = result

        binding.result.text = with(result.scores) {
            "${first.name}: ${first.goals}, ${second.name}: ${second.goals}"
        }
    }
}