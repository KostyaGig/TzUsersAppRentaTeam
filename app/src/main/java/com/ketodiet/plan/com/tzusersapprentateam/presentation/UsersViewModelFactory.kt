package com.ketodiet.plan.com.tzusersapprentateam.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ketodiet.plan.com.tzusersapprentateam.domain.UsersInteractor
import com.ketodiet.plan.com.tzusersapprentateam.presentation.state.UiUserStateCommunication
import com.ketodiet.plan.com.tzusersapprentateam.presentation.state.UiUserToUiCacheStateUser
import com.ketodiet.plan.com.tzusersapprentateam.presentation.state.UiUserToUiCommonStateUser
import com.ketodiet.plan.com.tzusersapprentateam.presentation.state.UiUsersToUiStateUserMapper
import io.reactivex.disposables.CompositeDisposable

interface UsersViewModelFactory : ViewModelProvider.Factory {

    class Base(
        private val interactor: UsersInteractor
    ) : UsersViewModelFactory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = UsersViewModel.Base(
                interactor,
                UiUserStateCommunication.Base(),
                DomainToUiUsersMapper.Base(
                    DomainToUiUserMapper.Base()
                ),
                UiUsersToUiStateUserMapper.Base(
                    UiUserToUiCommonStateUser.Base(),
                    UiUserToUiCacheStateUser.Base()
                ),
                DisposableStore.Base(
                    CompositeDisposable()
                )
            ) as T

    }
}