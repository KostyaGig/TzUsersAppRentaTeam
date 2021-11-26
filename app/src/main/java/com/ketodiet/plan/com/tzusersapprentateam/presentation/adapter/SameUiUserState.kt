package com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter

import com.ketodiet.plan.com.tzusersapprentateam.presentation.state.UiUserState

interface SameUiUserState {

    fun same(item: UiUserState) : Boolean

    fun sameId(item: UiUserState) : Boolean

    fun same(email: String,firstName: String) : Boolean

    fun sameId(id: Int) : Boolean
}