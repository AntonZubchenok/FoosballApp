package com.example.foosballapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: Map<Class<out ViewModel>, ViewModel>
) : ViewModelProvider.AndroidViewModelFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass] as T
    }
}