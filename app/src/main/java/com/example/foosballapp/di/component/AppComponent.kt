package com.example.foosballapp.di.component

import android.app.Application
import com.example.foosballapp.FoosballApp
import com.example.foosballapp.di.module.ActivityBindingModule
import com.example.foosballapp.di.module.AppModule
import com.example.foosballapp.di.module.DataModule
import com.example.foosballapp.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        AppModule::class,
        ViewModelModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<FoosballApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}