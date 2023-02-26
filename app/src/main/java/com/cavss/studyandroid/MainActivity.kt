package com.cavss.studyandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cavss.studyandroid.databinding.ActivityMainBinding
import com.cavss.studyandroid.databinding.ItemMainBinding
import com.cavss.studyandroid.recyclerview.BaseAdapter
import com.cavss.studyandroid.recyclerview.BaseDiffUtil
import com.cavss.studyandroid.recyclerview.BaseViewHolder
import com.cavss.studyandroid.recyclerview.ViewHolderClickListener
import com.cavss.studyandroid.ui.menu.MenuModel
import com.cavss.studyandroid.ui.menu.MenuVM
import com.cavss.studyandroid.BR



class MainActivity : AppCompatActivity() {

    /*
    'private lateinit var menuVM : MenuVM' 대신 'private var menuVM by viewModels<MenuVM>' 을 사용한 이유.
    1. Null 안정성 :
        'lateinit'을 사용하면 VM이 초기화 되지 않은 경우 Exception발생.
        반대로 by viewModels는 null safety하며, VM이 초기화 되지 않은 경우 시스템이 자동적으로 새 인스턴스를 생성.
    2. 코드 간소화 : VM의 인스턴스를 생성하고 관리하는 코드를 간소화 할 수 있으며 VM의 생명주기를 더 쉽게 관리.
    3. 범용성 : Dagger와 같은 의존성 주입 프레임워크를 사용하여 VM을 주입가능.
     */
    private val menuVM by viewModels<MenuVM>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.run{
            setMenuRecyclerView(menuRecyclerView)
        }
        setContentView(binding.root)


    }


    private var customAdapter : BaseAdapter.Adapter<MenuModel, ItemMainBinding>? = null
    private fun updateMenuList(newList : List<MenuModel>){
        try{
            customAdapter?.updateList(newList)
        }catch (e:Exception){
            Log.e("mEXception", "MainAcitivity, updateMenuList // Exception : ${e.message}")
        }
    }
    private fun setMenuRecyclerView(recyclerView : RecyclerView){
        try{
            val clickEvent = object : ViewHolderClickListener<MenuModel> {
                override fun onItemClick(model: MenuModel, position: Int) {
                    Log.d("mDebug", "MainActivity, setMenuRecyclerView, ViewHolderClickListener // Debug : ${model.menu} clicked")
                }
            }

            customAdapter = object : BaseAdapter.Adapter<MenuModel, ItemMainBinding>(){
//                override fun getDiffUtil(oldList: List<MenuModel>,newList: List<MenuModel>): BaseDiffUtil<MenuModel> {
//                    TODO("Not yet implemented")
//
//                }
//                override fun setViewHolderClass(binding: ItemMainBinding): BaseViewHolder<MenuModel, ItemMainBinding> {
//
//                }

                override fun setViewHolderXmlFileName(viewType: Int): Int {
                    return R.layout.item_main
                }

                override fun setViewHolderVariable(position: Int,model: MenuModel?): List<Pair<Int, Any>> {
                    return listOf(
                        BR.model to model!!,
                        BR.position to position,
                        BR.clickCallback to clickEvent
                    )
                }

            }

            recyclerView.apply {
                adapter = customAdapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity).apply{
                    orientation = LinearLayoutManager.VERTICAL
                    isItemPrefetchEnabled = false
                }
//                addItemDecoration(CustomItemGap(10))
                setItemViewCacheSize(0)
            }
        }catch (e:Exception){
            Log.e("mException", "MainActivity, setMenuRecyclerView // Exception : ${e.message}")
        }
    }

    override fun onStart() {
        super.onStart()

        menuVM.menuList.observe(this){ list : List<MenuModel> ->
            updateMenuList(list)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        customAdapter = null
    }
}