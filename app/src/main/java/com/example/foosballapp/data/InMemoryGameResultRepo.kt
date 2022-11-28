package com.example.foosballapp.data

import com.example.foosballapp.model.GameResult
import com.example.foosballapp.model.Score
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class InMemoryGameResultRepo @Inject constructor(
    // Other dependencies like local or remote data source should be injected here
) : GameResultRepo {

    private val gameResults = BehaviorSubject.createDefault(
        listOf(
            GameResult(0, Score("Amos", 4) to Score("Diego", 5)),
            GameResult(1, Score("Amos", 1) to Score("Diego", 5)),
            GameResult(2, Score("Amos", 2) to Score("Diego", 5)),
            GameResult(3, Score("Amos", 0) to Score("Diego", 5)),

            // 6 goals? might be a mistake
            GameResult(4, Score("Amos", 6) to Score("Diego", 5)),
            GameResult(5, Score("Amos", 5) to Score("Diego", 2)),
            GameResult(6, Score("Amos", 4) to Score("Diego", 0)),
            GameResult(7, Score("Joel", 4) to Score("Diego", 5)),
            GameResult(8, Score("Tim", 4) to Score("Amos", 5)),
            GameResult(9, Score("Tim", 5) to Score("Amos", 2)),
            GameResult(10, Score("Amos", 3) to Score("Tim", 5)),
            GameResult(11, Score("Amos", 5) to Score("Tim", 3)),
            GameResult(12, Score("Amos", 5) to Score("Joel", 4)),
            GameResult(13, Score("Joel", 5) to Score("Tim", 2))
        )
    )

    override fun getGameResults(): Observable<List<GameResult>> = gameResults

    override fun addGameResult(result: GameResult) {
        val results = gameResults.value?.toMutableList() ?: return
        val currentMaxId = results.maxByOrNull { it.id }?.id ?: -1
        results.add(result.copy(id = currentMaxId + 1))
        gameResults.onNext(results)
    }

    override fun updateGameResult(result: GameResult) {
        val results = gameResults.value?.toMutableList() ?: return
        val index = results.indexOfFirst { it.id == result.id }
        results.removeAt(index)
        results.add(index, result)
        gameResults.onNext(results)
    }
}