package com.ketodiet.plan.com.tzusersapprentateam.presentation.core

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.ketodiet.plan.com.tzusersapprentateam.core.UsersApp
import com.ketodiet.plan.com.tzusersapprentateam.presentation.nav.BottomNavigationActivity
import com.ketodiet.plan.com.tzusersapprentateam.presentation.fragment.UsersFragment

abstract class BaseFragment(@LayoutRes id: Int) : Fragment(id) {

    protected fun inject(fragment: BaseFragment) {
        val applicationComponent = (requireActivity().application as UsersApp).component
        when (fragment) {
            is UsersFragment -> applicationComponent.inject(fragment)
        }
    }

    protected val toolbar by lazy {
        checkNotNull((requireActivity() as MainActivity).supportActionBar)
    }

    protected companion object {
        const val USER_KEY = "user_key"
    }

    abstract fun navigateToBack()
}