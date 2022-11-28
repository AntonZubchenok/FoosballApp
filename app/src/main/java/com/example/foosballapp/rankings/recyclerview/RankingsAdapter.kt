package com.example.foosballapp.rankings.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class RankingsAdapter : ListAdapter<String, RankingsViewHolder>(RankingsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingsViewHolder {
        return RankingsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RankingsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RankingsDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return true
        }
    }
}

