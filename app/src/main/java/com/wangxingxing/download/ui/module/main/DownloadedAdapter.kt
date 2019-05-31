package com.wangxingxing.download.ui.module.main

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wangxingxing.download.R
import com.wangxingxing.download.bean.Data


class DownloadedAdapter(musicList: List<Data>) : BaseQuickAdapter<Data, BaseViewHolder>(R.layout.item_local_music_single, musicList) {
    override fun convert(helper: BaseViewHolder, item: Data) {
        //是否正在播放

    }
}