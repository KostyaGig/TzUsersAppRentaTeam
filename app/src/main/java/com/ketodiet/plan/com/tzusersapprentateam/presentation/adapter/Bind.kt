package com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter

import android.widget.ImageView
import android.widget.TextView

interface Bind {
    fun bind(avatarImage: ImageView, firstNameText: TextView, lastNameText: TextView, emailText: TextView)

    fun bind(errorText: TextView)
}