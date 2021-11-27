package com.ketodiet.plan.com.tzusersapprentateam.presentation

import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.squareup.picasso.Picasso
import kotlinx.android.parcel.Parcelize

interface ClickedUser : Abstract.User, Parcelable{

    fun fullUi(avatarImage: ImageView, textViews: List<TextView>)

    fun handleTitleToolbar(actionBar: ActionBar)

    @Parcelize
    data class Base(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : ClickedUser {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
            = mapper.map(id, email, firstName, lastName, avatar)

        override fun fullUi(avatarImage: ImageView, textViews: List<TextView>) {
            Picasso.get().load(avatar).into(avatarImage)
            textViews[0].text = firstName
            textViews[1].text = lastName
            textViews[2].text = email
        }

        override fun handleTitleToolbar(actionBar: ActionBar) {
            actionBar.title = "$firstName $lastName"
        }

    }
}