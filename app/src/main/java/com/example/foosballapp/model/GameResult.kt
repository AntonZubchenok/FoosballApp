package com.example.foosballapp.model

data class GameResult(
    val id: Int = -1,
    val scores: Pair<Score, Score>
)