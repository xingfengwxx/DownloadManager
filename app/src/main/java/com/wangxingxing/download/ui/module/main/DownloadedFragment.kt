package com.wangxingxing.download.ui.module.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wangxingxing.download.R
import com.wangxingxing.download.bean.Data
import kotlinx.android.synthetic.main.fragment_downloaded.*
import org.greenrobot.eventbus.EventBus

class DownloadedFragment : Fragment() {

    private val mData = mutableListOf<Data>()
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
        recyclerView.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, view, position ->
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun showDownloadedList() {
        mData.clear()
        mData.reverse()
        mAdapter.setNewData(mData)
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
