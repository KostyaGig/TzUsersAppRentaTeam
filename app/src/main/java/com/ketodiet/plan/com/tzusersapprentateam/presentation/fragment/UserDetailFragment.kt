package com.ketodiet.plan.com.tzusersapprentateam.presentation.fragment

import android.os.Bundle
import android.view.View
import com.ketodiet.plan.com.tzusersapprentateam.R
import com.ketodiet.plan.com.tzusersapprentateam.databinding.UserDetailFragmentBinding
import com.ketodiet.plan.com.tzusersapprentateam.presentation.ClickedUser
import com.ketodiet.plan.com.tzusersapprentateam.presentation.core.BaseFragment
import com.ketodiet.plan.com.tzusersapprentateam.presentation.nav.BottomNavigationActivity

class UserDetailFragment : BaseFragment(R.layout.user_detail_fragment) {

    private var _binding: UserDetailFragmentBinding? = null

    private val binding by lazy {
        checkNotNull(_binding)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = UserDetailFragmentBinding.bind(view)

        arguments?.let { bundle ->
            val clickedUser = checkNotNull(bundle.getParcelable<ClickedUser>(USER_KEY))

            val avatarImage = binding.avatarImage
            val firstNameText = binding.firstNameTv
            val lastNameText = binding.lastNameTv
            val emailText = binding.emailTv

            clickedUser.fullUi(avatarImage, listOf(firstNameText,lastNameText,emailText))
        }
    }

    override fun navigateToBack()
        = (requireActivity() as BottomNavigationActivity).selectItem(R.id.users_item)
}