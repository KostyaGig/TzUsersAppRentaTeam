package com.ketodiet.plan.com.tzusersapprentateam.presentation

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface DisposableStore {

    fun add(disposable: Disposable)

    fun clear()

    class Base(
        private val compositeDisposable: CompositeDisposable
    ) : DisposableStore {

        override fun add(disposable: Disposable) {
            compositeDisposable.add(disposable)
        }


        override fun clear()
            = compositeDisposable.dispose()
    }
}