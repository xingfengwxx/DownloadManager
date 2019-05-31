package com.wangxingxing.download.ui.module.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SizeUtils
import com.lzy.okserver.OkDownload
import com.lzy.okserver.task.XExecutor
import com.wangxingxing.download.R
import com.wangxingxing.download.event.UpdateListEvent
import com.wangxingxing.download.ui.widget.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_downloading_plus.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


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
        rvDownloading.addItemDecoration(SpacesItemDecoration(SizeUtils.dp2px(8f)))
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

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onUpdate(event: UpdateListEvent) {
        mAdapter.updateData(DownloadingAdapter.TYPE_ING)
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
