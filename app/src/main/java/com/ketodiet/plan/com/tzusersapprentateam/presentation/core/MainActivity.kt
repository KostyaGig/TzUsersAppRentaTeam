package com.ketodiet.plan.com.tzusersapprentateam.presentation.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ketodiet.plan.com.tzusersapprentateam.R
import com.ketodiet.plan.com.tzusersapprentateam.core.UsersApp
import com.ketodiet.plan.com.tzusersapprentateam.databinding.ActivityMainBinding
import com.ketodiet.plan.com.tzusersapprentateam.presentation.nav.BottomNavigationActivity
import com.ketodiet.plan.com.tzusersapprentateam.presentation.di.component.AppComponent
import com.ketodiet.plan.com.tzusersapprentateam.presentation.fragment.AboutProgramFragment
import com.ketodiet.plan.com.tzusersapprentateam.presentation.fragment.UsersFragment
import com.ketodiet.plan.com.tzusersapprentateam.presentation.nav.ExitActivity
import com.ketodiet.plan.com.tzusersapprentateam.presentation.nav.Navigator
import timber.log.Timber

fun Any?.log(message: String) {
    Timber.tag("zinoviewk").d(message)
}

class MainActivity : AppCompatActivity(), Navigator, BottomNavigationActivity, ExitActivity {

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

        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.users_item -> {
                    log("nav listener nav to UsersFragment")
                    navigateTo(UsersFragment())
                }
                R.id.about_program_item -> {
                    log("nav listener nav to UsersFragment")
                    navigateTo(AboutProgramFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        selectItem(R.id.users_item)
    }

    private fun Activity.component(): AppComponent {
        val application = application
        return if (application is UsersApp) {
            application.component
        } else {
            throw IllegalArgumentException("Application $application is not UsersApp")
        }
    }

    override fun selectItem(itemId: Int) {
        binding.bottomNav.selectedItemId = itemId
    }

    override fun navigateTo(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.fragments[0] as BaseFragment
        fragment.navigateToBack()
    }

    override fun exit() = finish()
}