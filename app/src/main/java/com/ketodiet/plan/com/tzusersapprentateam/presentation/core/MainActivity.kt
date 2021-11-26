package com.ketodiet.plan.com.tzusersapprentateam.presentation.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ketodiet.plan.com.tzusersapprentateam.core.UsersApp
import com.ketodiet.plan.com.tzusersapprentateam.databinding.ActivityMainBinding
import com.ketodiet.plan.com.tzusersapprentateam.presentation.UsersViewModel
import com.ketodiet.plan.com.tzusersapprentateam.presentation.UsersViewModelFactory
import com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter.UsersAdapter
import com.ketodiet.plan.com.tzusersapprentateam.presentation.di.component.AppComponent
import timber.log.Timber
import javax.inject.Inject

//todo remove later
fun Any?.log(message: String) {
    Timber.tag("zinoviewk").d(message)
}

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var usersViewModelFactory: UsersViewModelFactory

    private val usersViewModel: UsersViewModel by viewModels() {
        usersViewModelFactory
    }

    private var _binding: ActivityMainBinding? = null
    private val binding by lazy {
        checkNotNull(_binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component().inject(this)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        val adapter = UsersAdapter.Base()
        binding.usersRecView.adapter = adapter

        usersViewModel.observe(this) { uiStateUser ->
            adapter.update(uiStateUser)
        }

        usersViewModel.users()
    }

    private fun Activity.component() : AppComponent {
        val application = application
        return if(application is UsersApp) {
            application.component
        } else {
            throw IllegalArgumentException("Application $application is not UsersApp")
        }
    }
}