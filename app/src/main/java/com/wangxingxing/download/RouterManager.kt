package com.wangxingxing.download

import com.alibaba.android.arouter.launcher.ARouter

class RouterManager {
    companion object {
        const val URL_MAIN = "/main/main"
        const val URL_SPLASH = "/splash/splash"
        const val URL_ABOUT = "/about/about"

        fun goSplash() {
            ARouter.getInstance().build(URL_SPLASH).navigation()
        }

        fun goMain() {
            ARouter.getInstance().build(URL_MAIN).navigation()
        }

        fun goAbout() {
            ARouter.getInstance().build(URL_ABOUT).navigation()
        }
    }
}