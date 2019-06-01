package com.wangxingxing.download.db

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.wangxingxing.download.BaseApplication
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

object ObjectBox {

    lateinit var boxStore: BoxStore
        private set

    fun init(context: Context) {
        boxStore = MyObjectBox.builder().androidContext(context.applicationContext).build()

        if (BaseApplication.instance.isDebug()) {
            AndroidObjectBrowser(boxStore).start(context.applicationContext)
            LogUtils.d("Using ObjectBox ${BoxStore.getVersion()} (${BoxStore.getVersionNative()})")
        }
    }
}