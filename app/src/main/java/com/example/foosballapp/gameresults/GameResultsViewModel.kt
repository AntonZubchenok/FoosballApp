package com.example.foosballapp.gameresults

import androidx.lifecycle.ViewModel
import com.example.foosballapp.model.GameResult
import com.example.foosballapp.data.GameResultRepo
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GameResultsViewModel @Inject constructor(
    private val repo: GameResultRepo
) : ViewModel() {

    val gameResults: Observable<List<GameResult>> = repo.getGameResults()

    fun saveGameResult(result: GameResult) {
        repo.addGameResult(result)
    }

    fun updateGameResult(result: GameResult) {
        repo.updateGameResult(result)
    }
}