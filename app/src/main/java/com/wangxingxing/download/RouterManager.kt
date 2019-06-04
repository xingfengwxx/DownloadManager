package com.wangxingxing.download

import com.alibaba.android.arouter.launcher.ARouter

class RouterManager {
    companion object {
        const val URL_MAIN = "/main/main"
        const val URL_SPLASH = "/splash/splash"
        const val URL_ABOUT = "/about/about"
        const val URL_SETTINGS = "/settings/settings"
        const val URL_VIDEO_PLAYER = "/video/videoPlayer"

        fun goSplash() {
            ARouter.getInstance().build(URL_SPLASH).navigation()
        }

        fun goMain() {
            ARouter.getInstance().build(URL_MAIN).navigation()
        }

        fun goAbout() {
            ARouter.getInstance().build(URL_ABOUT).navigation()
        }

        fun goSettings() {
            ARouter.getInstance().build(URL_SETTINGS).navigation()
        }

        fun goVideoPlayer(url: String, title: String) {
            ARouter.getInstance().build(URL_VIDEO_PLAYER)
                .withString("mUrl", url)
                .withString("mTitle", title)
                .navigation()
        }
    }
}