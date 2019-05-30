package com.wangxingxing.download.ui.module.splash

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ScreenUtils
import com.wangxingxing.download.R
import com.wangxingxing.download.RouterManager
import com.wangxingxing.download.ui.dialog.DialogHelper

@Route(path = RouterManager.URL_SPLASH)
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //全屏
        ScreenUtils.setFullScreen(this)
        setContentView(R.layout.activity_splash)

        checkPermission()
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission()
        } else {
            toMain()
        }
    }

    private fun toMain() {
        window.decorView.postDelayed({
            RouterManager.goMain()
            finish()
        }, 2000)
    }

    private fun requestPermission() {
        PermissionUtils.permission(PermissionConstants.STORAGE)
            .rationale { shouldRequest -> DialogHelper.showRationaleDialog(shouldRequest) }
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(permissionsGranted: List<String>) {
                    LogUtils.d(permissionsGranted)
                    toMain()
                }

                override fun onDenied(permissionsDeniedForever: List<String>,
                                      permissionsDenied: List<String>) {
                    LogUtils.d(permissionsDeniedForever, permissionsDenied)
                    if (!permissionsDeniedForever.isEmpty()) {
                        DialogHelper.showOpenAppSettingDialog()
                        return
                    }
                    requestPermission()
                }
            })
            .theme { activity -> ScreenUtils.setFullScreen(activity) }
            .request()
    }
}
