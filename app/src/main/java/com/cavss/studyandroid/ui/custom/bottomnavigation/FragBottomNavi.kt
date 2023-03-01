package com.cavss.studyandroid.ui.custom.bottomnavigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.cavss.studyandroid.R
import com.cavss.studyandroid.databinding.FragmentBottomnaviBinding
import com.cavss.studyandroid.ui.screen.menu.MenuVM
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class FragBottomNavi : Fragment() {

    private val menuVM : MenuVM by activityViewModels()
    private lateinit var binding : FragmentBottomnaviBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try{
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottomnavi, container, false)
            binding.run{
                setBottomNavigation(bottomNavigation)
            }
        }catch (e:Exception){
            Log.e("mException", "FragBottomNavi, onCreateView // Exception : ${e.message}")
        }finally {
            return binding.root
        }
    }

    private fun setBottomNavigation(bottomNavi : BottomNavigationView){
        try{
            bottomNavi.setOnItemSelectedListener { menuItem ->
                when (menuItem.title) {
                    requireContext().getString(R.string.bottom_navi_item1) -> {
                        // Home Fragment로 이동
                        true
                    }
                    requireContext().getString(R.string.bottom_navi_item2) -> {
                        // Settings Fragment로 이동
                        true
                    }
                    requireContext().getString(R.string.bottom_navi_item3)-> {
                        true
                    }
                    requireContext().getString(R.string.bottom_navi_item4) -> {
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }catch (e:Exception){
            Log.e("mException", "FragBottomNavi, setBottomNavigation // Exception : ${e.localizedMessage}")
        }
    }

    override fun onStart() {
        super.onStart()

    }


    override fun onDestroy() {
        try{
            super.onDestroy()
        }catch (e:Exception){
            Log.e("mException", "FragBottomNavi, onDestroy // Exception : ${e.localizedMessage}")
        }
    }
}