package com.example.foosballapp.di.module

import com.example.foosballapp.data.GameResultRepo
import com.example.foosballapp.data.InMemoryGameResultRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideRepo(repo: InMemoryGameResultRepo): GameResultRepo
}