package com.example.foosballapp.gameresults.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.foosballapp.model.GameResult

class GameResultsAdapter(
    private val onItemClicked: (GameResult) -> Unit
) : ListAdapter<GameResult, GameResultViewHolder>(GameResultDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameResultViewHolder {
        return GameResultViewHolder(parent, onItemClicked)
    }

    override fun onBindViewHolder(holder: GameResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GameResultDiffCallback : DiffUtil.ItemCallback<GameResult>() {

        override fun areItemsTheSame(oldItem: GameResult, newItem: GameResult): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GameResult, newItem: GameResult): Boolean =
            oldItem.scores.first == newItem.scores.first && oldItem.scores.second == newItem.scores.second
    }
}

