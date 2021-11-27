package com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ketodiet.plan.com.tzusersapprentateam.databinding.FailureItemBinding
import com.ketodiet.plan.com.tzusersapprentateam.databinding.ProgressItemBinding
import com.ketodiet.plan.com.tzusersapprentateam.databinding.UserItemBinding
import com.ketodiet.plan.com.tzusersapprentateam.presentation.state.UiUserState

interface UsersAdapter {

    fun update(newList: List<UiUserState>)

    class Base(
        private val onItemClickListener: OnItemClickListener
    ) : UsersAdapter, RecyclerView.Adapter<Base.ViewHolder>() {

        private val users = ArrayList<UiUserState>()

        override fun update(newList: List<UiUserState>) {
            val diffUtilCallback = UsersDiffUtilCallback(users,newList)
            val result = DiffUtil.calculateDiff(diffUtilCallback)
            users.clear()
            users.addAll(newList)
            result.dispatchUpdatesTo(this)
        }

        private companion object {

            private const val PROGRESS = 1
            private const val BASE = 2
            private const val FAILURE = 3
        }

        override fun getItemViewType(position: Int): Int {
            return when(users[position]) {
                is UiUserState.Progress -> PROGRESS
                is UiUserState.Base -> BASE
                else -> FAILURE
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return when (viewType) {
                PROGRESS -> ViewHolder.Progress(
                    ProgressItemBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
                BASE -> ViewHolder.Base(
                    UserItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onItemClickListener
                )
                else -> ViewHolder.Failure(
                    FailureItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val user = users[position]
            holder.bind(user)
        }

        override fun getItemCount(): Int
            = users.size

        abstract class ViewHolder(
            view: View
        ) : RecyclerView.ViewHolder(view) {

            open fun bind(user: UiUserState) {}

            class Progress(view: ProgressItemBinding) : ViewHolder(view.root)

            class Base(
                private val view: UserItemBinding,
                private val onItemClickListener: OnItemClickListener
                ) : ViewHolder(view.root) {

                    override fun bind(user: UiUserState) {
                        user.bind(view.firstNameTv,view.lastNameTv)

                        view.root.setOnClickListener {
                            user.onItemClick(onItemClickListener)
                        }
                    }
            }
            class Failure(private val view: FailureItemBinding) : ViewHolder(view.root) {

                override fun bind(user: UiUserState)
                    = user.bind(view.errorTv)
            }
        }
    }
}
