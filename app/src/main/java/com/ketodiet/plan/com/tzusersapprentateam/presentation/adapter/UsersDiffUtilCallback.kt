package com.ketodiet.plan.com.tzusersapprentateam.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ketodiet.plan.com.tzusersapprentateam.presentation.state.UiUserState

class UsersDiffUtilCallback(
    private val oldList: List<UiUserState>,
    private val newList: List<UiUserState>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int
        = oldList.size

    override fun getNewListSize(): Int
        = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.sameId(newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.same(newItem)
    }
}