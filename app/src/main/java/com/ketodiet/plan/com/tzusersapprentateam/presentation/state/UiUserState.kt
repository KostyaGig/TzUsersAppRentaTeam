package com.ketodiet.plan.com.tzusersapprentateam.presentation.state

import android.widget.ImageView
import android.widget.TextView
import com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter.Bind
import com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter.SameUiUserState
import com.squareup.picasso.Picasso

sealed class UiUserState : SameUiUserState, Bind {


    override fun sameId(item: UiUserState): Boolean = false
    override fun same(item: UiUserState): Boolean = false

    override fun sameId(id: Int): Boolean = false
    override fun same(email: String, firstName: String): Boolean = false

    override fun bind(errorText: TextView)
        = Unit

    override fun bind(
        avatarImage: ImageView,
        firstNameText: TextView,
        lastNameText: TextView,
        emailText: TextView
    ) = Unit

    object Progress : UiUserState()

    class Base(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : UiUserState() {

        override fun sameId(item: UiUserState): Boolean
            = item.sameId(id)

        override fun sameId(id: Int): Boolean
            = this.id == id

        override fun same(item: UiUserState): Boolean
            = item.same(email, firstName)

        override fun same(email: String, firstName: String): Boolean
            = this.email == email && this.firstName == firstName

        override fun bind(avatarImage: ImageView,firstNameText: TextView,lastNameText: TextView,emailText: TextView) {
            Picasso.get().load(avatar).into(avatarImage)
            firstNameText.text = firstName
            lastNameText.text = lastName
            emailText.text = email
        }

    }

    class Failure(
        private val message: String
    ) : UiUserState() {

        override fun bind(errorText: TextView) {
            errorText.text = message
        }
    }
}
