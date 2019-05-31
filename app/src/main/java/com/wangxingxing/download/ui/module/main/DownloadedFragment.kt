package com.wangxingxing.download.ui.module.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.SizeUtils
import com.lzy.okgo.db.DownloadManager
import com.lzy.okserver.OkDownload
import com.lzy.okserver.download.DownloadTask
import com.wangxingxing.download.R
import com.wangxingxing.download.event.UpdateListEvent
import com.wangxingxing.download.ui.widget.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_downloaded.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DownloadedFragment : Fragment() {

    private val mData = mutableListOf<DownloadTask>()
    private lateinit var mAdapter: DownloadedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_downloaded, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
        showDownloadedList()
    }

    private fun init() {
        mAdapter = DownloadedAdapter(mData)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(SpacesItemDecoration(SizeUtils.dp2px(8f)))
        recyclerView.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, view, position ->
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun showDownloadedList() {
        mData.clear()
        mData.addAll(OkDownload.restore(DownloadManager.getInstance().finished))
        mAdapter.setNewData(mData)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUpdate(event: UpdateListEvent) {
        showDownloadedList()
    }

    companion object {
        fun newInstance(): DownloadedFragment {
            val args = Bundle()
            val fragment = DownloadedFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
