package com.cavss.studyandroid.ui.screen.menu

enum class Menu(val rawValue : String) {
    RecyclerView("RecyclerView"),
    LocationBasedAR("LocationBasedAR"),
    Fragment("Fragment"),
    ViewPager2("ViewPager2"),
    GridView("GridView"),
    Blur("Blur");

    override fun toString(): String {
        return rawValue
    }
}