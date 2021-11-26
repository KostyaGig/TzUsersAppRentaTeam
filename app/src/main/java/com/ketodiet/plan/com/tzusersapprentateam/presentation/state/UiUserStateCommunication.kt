package com.ketodiet.plan.com.tzusersapprentateam.presentation.state

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ketodiet.plan.com.tzusersapprentateam.presentation.core.Observe

interface UiUserStateCommunication : Observe<List<UiUserState>> {

    fun postValue(state: List<UiUserState>)

    class Base : UiUserStateCommunication {

        private val liveData = MutableLiveData<List<UiUserState>>()

        override fun observe(owner: LifecycleOwner, observer: Observer<List<UiUserState>>)
            = liveData.observe(owner, observer)

        override fun postValue(state: List<UiUserState>) {
            liveData.value = state
        }

    }
}