package com.cavss.studyandroid.ui.menu

class MenuRepository {

    private var menuList : List<MenuModel> = listOf<MenuModel>()

    suspend fun getMenuList(): List<MenuModel> {return menuList
    }

    fun setMenuList(newList: List<MenuModel>) {
        menuList = newList
    }
}