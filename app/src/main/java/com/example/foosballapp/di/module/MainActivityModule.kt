package com.example.foosballapp.di.module

import com.example.foosballapp.di.scope.FragmentScope
import com.example.foosballapp.gameresults.GameResultsFragment
import com.example.foosballapp.rankings.RankingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun gameResultsFragment(): GameResultsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun rankingsFragment(): RankingsFragment

}