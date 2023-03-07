package com.cavss.studyandroid.ui.custom.ar

import android.app.Activity
import android.content.Context
//import android.util.Log
//import com.google.ar.core.ArCoreApk
//import com.google.ar.core.Config
//import com.google.ar.core.Session
//import com.google.ar.core.exceptions.UnavailableException
//
//class ArManager {
//
//    // ARCore가 설치되어 있고 최신 버전을 사용 중인지 확인합니다.
//    fun availableARCore(context : Activity): Boolean {
//        return when (ArCoreApk.getInstance().checkAvailability(context)) {
//            // 통과
//            ArCoreApk.Availability.SUPPORTED_INSTALLED -> {
//                Log.d("mDebug", "ARCore 설치 통과됨.")
//                true
//            }
//
//            // ARCore버전이 오래되었거나, 설치되지 않은 경우
//            ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD, ArCoreApk.Availability.SUPPORTED_NOT_INSTALLED -> {
//                try {
//                    // ARCore가 설치되어 있지 않거나 최신 버전이 아닌 경우, 설치 또는 업데이트를 요청합니다.
//                    when (ArCoreApk.getInstance().requestInstall(context, true)) {
//                        ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
//                            Log.d("mDebug", "ARCore 설치 요청됨.")
//                            false
//                        }
//                        ArCoreApk.InstallStatus.INSTALLED -> {
//                            Log.d("mDebug", "ARCore 설치가 되어있긴함.")
//                            true
//                        }
//                    }
//                } catch (e: UnavailableException) {
//                    // ARCore가 설치되어 있지 않은 경우 예외가 발생합니다.
//                    Log.e("mException", "ArManager, availableARCore // Exception : ${e.message}")
//                    false
//                }
//            }
//
//            // 이 장치는 AR을 지원하지 않습니다
//            ArCoreApk.Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE -> {
//                Log.d("mDebug", "해당 기기는 ARCore가 지원되지 않음.")
//                false
//            }
//
//            // ARCore는 원격 쿼리를 사용하여 가용성을 확인합니다.
//            ArCoreApk.Availability.UNKNOWN_CHECKING -> {
//                // 이 함수는 200ms 대기한 후 쿼리 결과를 확인하기 위해 다시 호출해야 합니다.
//                Log.d("mDebug", "ARCore에서 뭔가 이상이 생겨 쿼리 다시 요청중.")
//                false
//            }
//
//            // AR 가용성을 확인하는 중 오류가 발생했습니다. 장치가 오프라인인 경우가 있을 수 있습니다.
//            ArCoreApk.Availability.UNKNOWN_ERROR, ArCoreApk.Availability.UNKNOWN_TIMED_OUT -> {
//                // 오류를 적절하게 처리합니다.
//                Log.d("mDebug", "AR 가용성을 확인하는 중 오류가 발생했습니다. 장치가 오프라인인 경우가 있을 수 있습니다.")
//                false
//            }
//        }
//    }
//
////    fun createSession(context : Context) {
////        // Create a new ARCore session.
////        session = Session(context)
////
////        // Create a session config.
////        val config = Config(session)
////
////        // Do feature-specific operations here, such as enabling depth or turning on
////        // support for Augmented Faces.
////
////        // Configure the session.
////        session.configure(config)
////    }
//}