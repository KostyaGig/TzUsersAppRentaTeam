package com.ketodiet.plan.com.tzusersapprentateam.presentation.state

import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.ketodiet.plan.com.tzusersapprentateam.core.Abstract
import com.ketodiet.plan.com.tzusersapprentateam.presentation.ToClickedUserMapper
import com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter.Bind
import com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter.OnItemClickListener
import com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter.SameUiUserState

sealed class UiUserState : Abstract.User,SameUiUserState, Bind {

    override fun <T> map(mapper: Abstract.UserMapper<T>): T
        = mapper.map(-1,"","","","")

    override fun sameId(item: UiUserState): Boolean = false
    override fun same(item: UiUserState): Boolean = false

    override fun sameId(id: Int): Boolean = false
    override fun same(email: String, firstName: String): Boolean = false

    override fun bind(errorText: TextView)
        = Unit

    override fun bind(
        firstNameText: TextView,
        lastNameText: TextView
    ) = Unit

    open fun handleTitleToolbar(actionBar: ActionBar) = Unit

    open fun onItemClick(onItemClickListener: OnItemClickListener) = Unit

    object Progress : UiUserState() {

        override fun handleTitleToolbar(actionBar: ActionBar) {
            actionBar.title = TITLE_TOOLBAR
        }

        private const val TITLE_TOOLBAR = "Progress..."
    }

    abstract class Base(
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

        override fun bind(firstNameText: TextView, lastNameText: TextView) {
            firstNameText.text = firstName
            lastNameText.text = lastName
        }

        abstract fun titleToolbar() : String

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
            = mapper.map(id, email, firstName, lastName, avatar)

        override fun handleTitleToolbar(actionBar: ActionBar) {
            actionBar.title = titleToolbar()
        }

        override fun onItemClick(onItemClickListener: OnItemClickListener) {
            onItemClickListener.onItemClick(
                map(ToClickedUserMapper.Base())
            )
        }
    }

    class Common(
        id: Int,
        email: String,
        firstName: String,
        lastName: String,
        avatar: String
    ) : Base(id, email, firstName, lastName, avatar) {

        override fun titleToolbar(): String
            = TITLE_TOOLBAR

        private companion object {
            private const val TITLE_TOOLBAR = "UserApp(Cloud)"
        }
    }

    class Cache(
        id: Int,
        email: String,
        firstName: String,
        lastName: String,
        avatar: String
    ) : Base(id, email, firstName, lastName, avatar) {

        override fun titleToolbar(): String
            = TITLE_TOOLBAR

        private companion object {
            private const val TITLE_TOOLBAR = "UserApp(Cache)"
        }
    }

    class Failure(
        private val message: String
    ) : UiUserState() {

        override fun bind(errorText: TextView) {
            errorText.text = message
        }

        override fun handleTitleToolbar(actionBar: ActionBar) {
            actionBar.title = "$TITLE_TOOLBAR($message)"
        }

        private companion object {
            private const val TITLE_TOOLBAR = "Failure"
        }
    }
}
