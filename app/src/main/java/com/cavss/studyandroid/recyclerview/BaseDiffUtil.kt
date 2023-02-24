package com.cavss.studyandroid.recyclerview

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtil<MODEL>(
    private val oldList : List<MODEL>,
    private val newList : List<MODEL>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemsTheSame(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])
    }

    abstract fun areItemsTheSame(oldItem: MODEL, newItem: MODEL): Boolean

    abstract fun areContentsTheSame(oldItem: MODEL, newItem: MODEL): Boolean
}