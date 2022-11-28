package com.example.foosballapp.data

import com.example.foosballapp.model.GameResult
import io.reactivex.rxjava3.core.Observable

interface GameResultRepo {

    fun getGameResults(): Observable<List<GameResult>>

    fun addGameResult(result: GameResult)

    fun updateGameResult(result: GameResult)
}