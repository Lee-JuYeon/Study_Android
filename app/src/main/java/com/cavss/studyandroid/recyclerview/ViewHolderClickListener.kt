package com.cavss.studyandroid.recyclerview

interface ViewHolderClickListener<MODEL> {
    fun onItemClick(model : MODEL, position : Int)
}