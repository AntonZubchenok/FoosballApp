package com.example.foosballapp.util

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.disposeOnDestroy(lifecycleOwner: LifecycleOwner) {
    lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            this@disposeOnDestroy.dispose()
        }
    })
}