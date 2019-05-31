package com.wangxingxing.download.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class DownloadService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return DownloadBinder()
    }

    inner class DownloadBinder : Binder() {

    }

}