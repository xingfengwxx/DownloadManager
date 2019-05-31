package com.wangxingxing.download.ui.module.main

import android.text.format.Formatter
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lzy.okserver.download.DownloadTask
import com.wangxingxing.download.R
import com.wangxingxing.download.utils.FormatUtils


class DownloadedAdapter(list: List<DownloadTask>) : BaseQuickAdapter<DownloadTask, BaseViewHolder>(R.layout.item_local_music_single, list) {
    override fun convert(helper: BaseViewHolder, item: DownloadTask) {
        //是否正在播放
        helper.setText(R.id.tvName, item.progress.fileName)
                .setText(R.id.tvSize, Formatter.formatFileSize(mContext, item.progress.totalSize))

        val imageView = helper.getView<ImageView>(R.id.ivType)
        when (FormatUtils.getFormatType(item.progress.fileName)) {
            FormatUtils.TYPE_APK    -> imageView.setImageResource(R.drawable.ic_format_apk)
            FormatUtils.TYPE_MUSIC  -> imageView.setImageResource(R.drawable.ic_format_music)
            FormatUtils.TYPE_VIDEO  -> imageView.setImageResource(R.drawable.ic_format_video)
            FormatUtils.TYPE_OTHERS -> imageView.setImageResource(R.drawable.ic_format_others)
        }

        helper.addOnClickListener(R.id.ivDelete)
    }
}