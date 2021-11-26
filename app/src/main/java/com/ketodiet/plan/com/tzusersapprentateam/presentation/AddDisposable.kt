package com.ketodiet.plan.com.tzusersapprentateam.presentation

import io.reactivex.disposables.Disposable

interface AddDisposable {

    fun Disposable.add(disposableStore: DisposableStore)
}