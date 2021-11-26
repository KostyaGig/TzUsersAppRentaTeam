package com.ketodiet.plan.com.tzusersapprentateam.presentation.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ketodiet.plan.com.tzusersapprentateam.R
import com.ketodiet.plan.com.tzusersapprentateam.core.UsersApp
import com.ketodiet.plan.com.tzusersapprentateam.databinding.ActivityMainBinding
import com.ketodiet.plan.com.tzusersapprentateam.presentation.di.component.AppComponent
import com.ketodiet.plan.com.tzusersapprentateam.presentation.fragment.UsersFragment
import timber.log.Timber

//todo remove later
fun Any?.log(message: String) {
    Timber.tag("zinoviewk").d(message)
}

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding by lazy {
        checkNotNull(_binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component().inject(this)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)
        setSupportActionBar(binding.toolbar)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,UsersFragment()).commit()

        //todo integrate nav graph with bottom nave
        //todo create details fragment and fragment "About app"
        // todo create simply README.md
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