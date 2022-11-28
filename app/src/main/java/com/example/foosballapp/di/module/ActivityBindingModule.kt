package com.example.foosballapp.di.module

import com.example.foosballapp.MainActivity
import com.example.foosballapp.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

}