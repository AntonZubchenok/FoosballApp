package com.example.foosballapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foosballapp.data.GameResultRepo
import com.example.foosballapp.di.ViewModelFactory
import com.example.foosballapp.di.ViewModelKey
import com.example.foosballapp.gameresults.GameResultsViewModel
import com.example.foosballapp.rankings.RankingsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @Provides
    fun provideViewModelFactory(
        map: Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>
    ): ViewModelProvider.AndroidViewModelFactory {
        return ViewModelFactory(map)
    }

    @Provides
    @IntoMap
    @ViewModelKey(GameResultsViewModel::class)
    fun provideGameResultsViewModel(gameResultRepo: GameResultRepo): GameResultsViewModel {
        return GameResultsViewModel(gameResultRepo)
    }

    @Provides
    @IntoMap
    @ViewModelKey(RankingsViewModel::class)
    fun provideRankingsViewModel(gameResultRepo: GameResultRepo): RankingsViewModel {
        return RankingsViewModel(gameResultRepo)
    }
}