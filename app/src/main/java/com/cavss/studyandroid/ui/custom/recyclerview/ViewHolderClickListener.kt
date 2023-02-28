package com.cavss.studyandroid.ui.custom.recyclerview

interface ViewHolderClickListener<MODEL> {
    fun onItemClick(model : MODEL, position : Int)
}