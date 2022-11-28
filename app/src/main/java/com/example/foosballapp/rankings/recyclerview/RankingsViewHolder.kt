package com.example.foosballapp.rankings.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.foosballapp.databinding.ItemRankingsBinding
import com.example.foosballapp.util.ViewBindingViewHolder

class RankingsViewHolder(
    parent: ViewGroup,
) : ViewBindingViewHolder<ItemRankingsBinding>(parent, ItemRankingsBinding::inflate) {

    fun bind(name: String) {
        binding.name.text = name
    }
}