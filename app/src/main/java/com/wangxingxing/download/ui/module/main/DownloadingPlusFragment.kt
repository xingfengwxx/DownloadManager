package com.wangxingxing.download.ui.module.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lzy.okserver.OkDownload
import com.lzy.okserver.task.XExecutor
import com.wangxingxing.download.R
import kotlinx.android.synthetic.main.fragment_downloading_plus.*


class DownloadingPlusFragment : Fragment(), XExecutor.OnAllTaskEndListener {
    private lateinit var mAdapter: DownloadingAdapter
    private lateinit var okDownload: OkDownload

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_downloading_plus, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
    }

    fun init() {
        okDownload = OkDownload.getInstance()
        okDownload.addOnAllTaskEndListener(this)

        mAdapter = DownloadingAdapter(context!!)
        mAdapter.updateData(DownloadingAdapter.TYPE_ING)
        rvDownloading.layoutManager = LinearLayoutManager(context)
        rvDownloading.adapter = mAdapter
    }

    override fun onAllTaskEnd() {
        //TODO(所有下载任务完成)
    }

    override fun onDestroy() {
        super.onDestroy()
        okDownload.removeOnAllTaskEndListener(this)
    }

    override fun onResume() {
        super.onResume()
        mAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): DownloadingPlusFragment {
            val args = Bundle()
            val fragment = DownloadingPlusFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
