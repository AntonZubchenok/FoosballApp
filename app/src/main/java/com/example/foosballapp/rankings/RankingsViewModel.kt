package com.example.foosballapp.rankings

import androidx.lifecycle.ViewModel
import com.example.foosballapp.data.GameResultRepo
import com.example.foosballapp.model.Score
import com.example.foosballapp.rankings.RankingsViewModel.SortType.GAMES
import com.example.foosballapp.rankings.RankingsViewModel.SortType.WINS
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class RankingsViewModel @Inject constructor(repo: GameResultRepo) : ViewModel() {

    private val _sortType = BehaviorSubject.createDefault(WINS)
    val sortType: Observable<SortType> = _sortType

    val players: Observable<List<String>> = Observable.combineLatest(
        repo.getGameResults()
            .map { results -> results.map { it.scores.first } + results.map { it.scores.second } },
        _sortType,
        ::sortPlayers
    )

    fun setSortType(sortType: SortType) {
        _sortType.onNext(sortType)
    }

    private fun sortPlayers(scores: List<Score>, sortType: SortType): List<String> {
        val players = scores.map { it.name }.distinct()
        val payersWithCount = mutableMapOf<String, Int>()
        when (sortType) {
            GAMES -> {
                players.forEach { player ->
                    val count = scores.count { it.name == player }
                    payersWithCount[player] = count
                }
            }
            WINS -> {
                scores.forEach { score ->
                    payersWithCount[score.name] = (payersWithCount[score.name] ?: 0) + score.goals
                }
            }
        }
        return payersWithCount.entries
            .sortedByDescending { it.value }
            .map { (name, count) -> "$name: $count" }
    }

    enum class SortType {
        GAMES, WINS
    }
}