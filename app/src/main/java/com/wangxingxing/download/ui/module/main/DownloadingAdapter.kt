package com.wangxingxing.download.ui.module.main

import android.content.Context
import android.text.format.Formatter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.daimajia.numberprogressbar.NumberProgressBar
import com.lzy.okgo.db.DownloadManager
import com.lzy.okgo.model.Progress
import com.lzy.okserver.OkDownload
import com.lzy.okserver.download.DownloadListener
import com.lzy.okserver.download.DownloadTask
import com.wangxingxing.download.R
import java.io.File
import java.text.NumberFormat

class DownloadingAdapter(context: Context) : RecyclerView.Adapter<DownloadingAdapter.ViewHolder>() {

    companion object {
        val TYPE_ALL = 0
        val TYPE_FINISH = 1
        val TYPE_ING = 2
    }

    private lateinit var values: List<DownloadTask>
    private lateinit var numberFormat: NumberFormat
    private lateinit var inflater: LayoutInflater
    private lateinit var context: Context
    private var type: Int = 0

    init {
        this.context = context
        numberFormat = NumberFormat.getPercentInstance()
        numberFormat.minimumFractionDigits = 2
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun updateData(type: Int) {
        //这里是将数据库的数据恢复
        this.type = type
        if (type == TYPE_ALL) values = OkDownload.restore(DownloadManager.getInstance().all)
        if (type == TYPE_FINISH) values = OkDownload.restore(DownloadManager.getInstance().finished)
        if (type == TYPE_ING) values = OkDownload.restore(DownloadManager.getInstance().downloading)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.ic_downloading_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (values == null) 0 else values.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = values[position]
        val tag = createTag(task)
        task.register(ListDownloadListener(tag, holder))
        holder.setTag(tag)
        holder.setTask(task)
        holder.bind()
        holder.refresh(task.progress)
    }

    private fun createTag(task: DownloadTask): String {
        return type.toString() + "_" + task.progress.tag
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name = view.findViewById<TextView>(R.id.name)
        var downloadSize = view.findViewById<TextView>(R.id.downloadSize)
        var netSpeed = view.findViewById<TextView>(R.id.netSpeed)
        var pbProgress = view.findViewById<NumberProgressBar>(R.id.pbProgress)
        var download = view.findViewById<ImageView>(R.id.start)
        var remove = view.findViewById<ImageView>(R.id.remove)
        private lateinit var task: DownloadTask
        private lateinit var tag: String

        init {
            download.setOnClickListener {
                start()
            }
            remove.setOnClickListener {
                remove()
            }
        }

        fun setTask(task: DownloadTask) {
            this.task = task
        }

        fun bind() {
            val progress = task.progress
            name.text = progress.fileName
        }

        fun refresh(progress: Progress) {
            val currentSize = Formatter.formatFileSize(context, progress.currentSize)
            val totalSize = Formatter.formatFileSize(context, progress.totalSize)
            downloadSize.text = "$currentSize/$totalSize"
            when (progress.status) {
                Progress.NONE -> {
                    netSpeed.text = "停止"
                    download.setImageResource(R.drawable.ic_manage_download)
                }
                Progress.PAUSE -> {
                    netSpeed.text = "暂停中"
                    download.setImageResource(R.drawable.ic_manage_download)
                }
                Progress.ERROR -> {
                    netSpeed.text = "下载出错"
                    download.setImageResource(R.drawable.ic_manage_download)
                }
                Progress.WAITING -> {
                    netSpeed.text = "等待中"
                    download.setImageResource(R.drawable.ic_manage_wait)
                }
                Progress.FINISH -> netSpeed.text = "下载完成"
                Progress.LOADING -> {
                    val speed = Formatter.formatFileSize(context, progress.speed)
                    netSpeed.text = String.format("%s/s", speed)
                    download.setImageResource(R.drawable.ic_manage_pause)
                }
            }
            pbProgress.max = 10000
            pbProgress.progress = (progress.fraction * 10000).toInt()
        }

        fun start() {
            val progress = task.progress
            when (progress?.status) {
                Progress.PAUSE, Progress.NONE, Progress.ERROR -> task.start()
                Progress.LOADING -> task.pause()
                Progress.FINISH -> {
                }
            }
            refresh(progress!!)
        }

        fun remove() {
            task.remove(true)
            updateData(type)
        }

        fun setTag(tag: String) {
            this.tag = tag
        }

        fun getTag(): String {
            return tag
        }
    }

    private inner class ListDownloadListener internal constructor(tag: Any, private val holder: ViewHolder) :
        DownloadListener(tag) {

        override fun onStart(progress: Progress) {}

        override fun onProgress(progress: Progress) {
            if (tag === holder.getTag()) {
                holder.refresh(progress)
            }
        }

        override fun onError(progress: Progress) {
            val throwable = progress.exception
            throwable?.printStackTrace()
        }

        override fun onFinish(file: File, progress: Progress) {
            LogUtils.i("onFinish: 下载完成：path=" + progress.filePath)
            updateData(type)
        }

        override fun onRemove(progress: Progress) {}
    }
}