package com.ketodiet.plan.com.tzusersapprentateam.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ketodiet.plan.com.tzusersapprentateam.R
import com.ketodiet.plan.com.tzusersapprentateam.databinding.UsersFragmentBinding
import com.ketodiet.plan.com.tzusersapprentateam.presentation.ClickedUser
import com.ketodiet.plan.com.tzusersapprentateam.presentation.UsersViewModel
import com.ketodiet.plan.com.tzusersapprentateam.presentation.UsersViewModelFactory
import com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter.OnItemClickListener
import com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter.UsersAdapter
import com.ketodiet.plan.com.tzusersapprentateam.presentation.core.BaseFragment
import com.ketodiet.plan.com.tzusersapprentateam.presentation.core.log
import com.ketodiet.plan.com.tzusersapprentateam.presentation.nav.ExitActivity
import com.ketodiet.plan.com.tzusersapprentateam.presentation.nav.Navigator
import javax.inject.Inject

class UsersFragment : BaseFragment(R.layout.users_fragment) {

    @Inject
    lateinit var usersViewModelFactory: UsersViewModelFactory

    private val usersViewModel: UsersViewModel by viewModels() {
        usersViewModelFactory
    }

    private var _binding: UsersFragmentBinding? = null
    private val binding by lazy {
        checkNotNull(_binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = UsersFragmentBinding.bind(view)

        val adapter = UsersAdapter.Base(object : OnItemClickListener {

            override fun onItemClick(user: ClickedUser) {
                val bundle = Bundle().apply {
                    putParcelable(USER_KEY,user)
                }

                val fragment = UserDetailFragment()
                fragment.arguments = bundle

                (requireActivity() as Navigator).navigateTo(fragment)
            }
        })
        binding.usersRecView.adapter = adapter

        usersViewModel.observe(this) { uiStateUser ->
            adapter.update(uiStateUser)
            uiStateUser.first().handleTitleToolbar(toolbar)
        }

        usersViewModel.users()
    }

    override fun navigateToBack()
        = (requireActivity() as ExitActivity).exit()

}