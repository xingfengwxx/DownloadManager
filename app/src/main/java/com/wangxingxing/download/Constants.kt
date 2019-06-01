package com.wangxingxing.download

import android.os.Environment
import java.io.File

object Constants {
    //path==========================================================================================
    //root
    val PATH_APP_ROOT = (Environment.getExternalStorageDirectory().path
            + File.separator + "VideoDownloadManager" + File.separator)
    //download
    val PATH_DOWNLOAD = PATH_APP_ROOT + "download" + File.separator
    //video
    val PATH_VIDEO = PATH_APP_ROOT + "video" + File.separator
    //apk
    val PATH_APK = PATH_APP_ROOT + "apk" + File.separator
    //log
    val PATH_LOG = PATH_APP_ROOT + "log" + File.separator

    //txt
    val GOOGLE_PLAY_URL = "https://play.google.com/store/apps/details?id=${BaseApplication.instance.packageName}"
}