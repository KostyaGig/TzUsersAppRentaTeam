package com.ketodiet.plan.com.tzusersapprentateam.presentation.fragment

import com.ketodiet.plan.com.tzusersapprentateam.R
import com.ketodiet.plan.com.tzusersapprentateam.presentation.core.BaseFragment
import com.ketodiet.plan.com.tzusersapprentateam.presentation.nav.BottomNavigationActivity

class AboutProgramFragment : BaseFragment(R.layout.about_program_fragment) {

    override fun navigateToBack()
        = (requireActivity() as BottomNavigationActivity).selectItem(R.id.users_item)
}