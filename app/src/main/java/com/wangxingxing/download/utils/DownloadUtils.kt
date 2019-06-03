package com.wangxingxing.download.utils

import com.blankj.utilcode.util.LogUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Progress
import com.lzy.okserver.OkDownload
import com.lzy.okserver.download.DownloadListener
import com.wangxingxing.download.Constants
import com.wangxingxing.download.event.UpdateListEvent
import org.greenrobot.eventbus.EventBus
import java.io.File

object DownloadUtils {

    const val TAG = "DownloadUtils"

    fun download(url: String) {
        val request = OkGo.get<File>(url)
        OkDownload.request(url, request)
            .folder(Constants.PATH_DOWNLOAD)
            .save()
            .register(object: DownloadListener(TAG) {
                override fun onFinish(t: File?, progress: Progress?) {
                    LogUtils.d("progress=$progress")
                    EventBus.getDefault().post(UpdateListEvent())
                }

                override fun onRemove(progress: Progress?) {
                    LogUtils.d("progress=$progress")
                    EventBus.getDefault().post(UpdateListEvent())
                }

                override fun onProgress(progress: Progress?) {
                    LogUtils.d("progress=$progress")
                }

                override fun onError(progress: Progress?) {
                    LogUtils.e("progress=$progress")
                }

                override fun onStart(progress: Progress?) {
                    LogUtils.d("progress=$progress")
                    EventBus.getDefault().post(UpdateListEvent())
                }

            })
            .start()
    }
}