package com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter

import android.widget.ImageView
import android.widget.TextView

interface Bind {
    fun bind(firstNameText: TextView, lastNameText: TextView)

    fun bind(errorText: TextView)
}