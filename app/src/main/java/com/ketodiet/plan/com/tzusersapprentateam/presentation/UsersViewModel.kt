package com.ketodiet.plan.com.tzusersapprentateam.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ketodiet.plan.com.tzusersapprentateam.domain.UsersInteractor
import com.ketodiet.plan.com.tzusersapprentateam.presentation.core.Observe
import com.ketodiet.plan.com.tzusersapprentateam.presentation.state.UiUserState
import com.ketodiet.plan.com.tzusersapprentateam.presentation.state.UiUserStateCommunication
import com.ketodiet.plan.com.tzusersapprentateam.presentation.state.UiUsersToUiStateUserMapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

interface UsersViewModel : Observe<List<UiUserState>>, AddDisposable {

    fun users()

    class Base(
        private val interactor: UsersInteractor,
        private val communication: UiUserStateCommunication,
        private val domainToUiUsersMapper: DomainToUiUsersMapper,
        private val uiUsersToUiStateUserMapper: UiUsersToUiStateUserMapper,
        private val disposableStore: DisposableStore
    ) : UsersViewModel,ViewModel() {

        override fun users() {
            communication.postValue(listOf(UiUserState.Progress))
            val domainUsers = interactor.users()
            val uiUsers = domainUsers.flatMap { domainUsers ->
                Single.just(domainUsers.map(
                    domainToUiUsersMapper
                ))
            }

            val uiUserState = uiUsers.flatMap { uiUsers ->
                Single.just(uiUsers.map(uiUsersToUiStateUserMapper))
            }
            uiUserState
                .subscribeOn(Schedulers.io())
                .delay(DELAY.toLong(),TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { state ->
                        communication.postValue(state)
                    },
                    {
                    }
                ).add(disposableStore)
        }

        private companion object {
            private const val DELAY = 1500
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<UiUserState>>)
            = communication.observe(owner, observer)

        override fun onCleared() {
            disposableStore.clear()
            super.onCleared()
        }

        override fun Disposable.add(disposableStore: DisposableStore)
            = disposableStore.add(this)
    }
}