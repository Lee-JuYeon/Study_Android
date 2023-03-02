package com.cavss.studyandroid.ui.custom.bottomnavigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BottomNavigationVM : ViewModel() {
    private val fragmentType = MutableLiveData<Int>(0)
    fun setFragmentType(type : Int){ fragmentType.postValue(type) }
    val getFragmentType : LiveData<Int>
        get() = fragmentType


    init {
        setFragmentType(0)
    }
}