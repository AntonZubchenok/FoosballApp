package com.example.foosballapp

import com.example.foosballapp.di.component.AppComponent
import com.example.foosballapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class FoosballApp : DaggerApplication() {

    private val appComponent: AppComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent
}