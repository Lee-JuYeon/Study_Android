package com.cavss.studyandroid.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class PermissionManager {


    private var context : FragmentActivity? = null
    fun setContext(fragmentActivity : FragmentActivity){
        this.context = fragmentActivity
    }

    private var permissionList : Array<String>? = null
    fun setPermissionList(list : Array<String>){
        this.permissionList = list
    }


    private val requestPermissionLauncher = context
        ?.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // 권한 허용시 처리할 작업 수행
            } else {
                // 권한 거부시 처리할 작업 수행
            }
        }

    fun checkPermissionList(
        onGranted : () -> Unit,
        onDenied : (Unit?) -> Unit
    ){
        try{
            context?.registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                // 각 권한이 승인되었는지 여부 확인
                val isAllPermissionsGranted = permissions.all { it.value == true }

                // 모든 권한이 승인된 경우
                if (isAllPermissionsGranted){
                    onGranted()
                }
            }?.launch(permissionList)

            permissionList?.forEachIndexed { index : Int, permission : String ->
                var mPermission = context?.checkSelfPermission(permission)
                if (mPermission ==  PackageManager.PERMISSION_DENIED){
                    // 권한 요청이 처음인 경우 OR 권한 거부시 '다시 묻지 않기'를 체크하지 않은 경우
                    if(context?.shouldShowRequestPermissionRationale(permission)!!){

                        // 해당 권한 재요청
                        onDenied(requestPermissionLauncher?.launch(permission))
                    }
                }
            }
        }catch (e:Exception){
            Log.e("mException", "PermissionManager, checkPermissionList // Exception : ${e.localizedMessage}")
        }
    }
}


/*
abstract class PermissionManager {

    abstract fun setContext() : FragmentActivity
    abstract fun setPermissionList() : Array<String>
    abstract fun onGranted() : () -> Unit

    private val requestPermissionsLauncher = setContext()
        .registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            // 각 권한이 승인되었는지 여부 확인
            val isAllPermissionsGranted = permissions.all { it.value == true }

            if (isAllPermissionsGranted){
                onGranted()
            }
        }

    private val requestPermissionLauncher = setContext()
        .registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
            } else {
            }
        }

    fun checkPermissions(){
        setPermissionList().forEachIndexed { index : Int, permission : String ->
            var mPermission = setContext().checkSelfPermission(permission)
            if (mPermission ==  PackageManager.PERMISSION_GRANTED){
                // granted permission
            }else{
                requestPermissionLauncher.launch(permission)
            }
        }
    }

    fun requestPermissions(){
        try{
            requestPermissionsLauncher.launch(setPermissionList())
        }catch (e:Exception){
            Log.e("mException", "PermissionManager, requestPermissions // Exception : ${e.localizedMessage}")
        }
    }
}
 */