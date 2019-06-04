package com.wangxingxing.download.ui.module.video

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ScreenUtils
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.wangxingxing.download.R
import com.wangxingxing.download.RouterManager
import com.wangxingxing.download.utils.FormatUtils
import kotlinx.android.synthetic.main.activity_video_player.*

@Route(path = RouterManager.URL_VIDEO_PLAYER)
class VideoPlayerActivity : AppCompatActivity() {

    @Autowired
    @JvmField
    var mUrl: String = ""

    @Autowired
    @JvmField
    var mTitle: String = ""

    private lateinit var orientationUtils: OrientationUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        ScreenUtils.setFullScreen(this)
        setContentView(R.layout.activity_video_player)

        initVideo()
    }

    private fun initVideo() {
        if (TextUtils.isEmpty(mUrl) || TextUtils.isEmpty(mTitle))
            return

        if (FormatUtils.getFormatType(mTitle) == FormatUtils.TYPE_MUSIC) {
            lav.visibility = View.VISIBLE
            lav.playAnimation()
        }

        videoPlayer.setUp(mUrl, true, mTitle)
        videoPlayer.titleTextView.visibility = View.VISIBLE
        videoPlayer.backButton.visibility = View.VISIBLE
        orientationUtils = OrientationUtils(this, videoPlayer)
        videoPlayer.fullscreenButton.setOnClickListener {
            orientationUtils.resolveByClick()
        }
        videoPlayer.setIsTouchWiget(true)
        videoPlayer.backButton.setOnClickListener {
            onBackPressed()
        }
        videoPlayer.startPlayLogic()
    }

    override fun onPause() {
        super.onPause()
        videoPlayer.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        videoPlayer.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        if (orientationUtils != null) {
            orientationUtils.releaseListener()
        }
    }

    override fun onBackPressed() {
        if (orientationUtils.screenType == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.fullscreenButton.performClick()
            return
        }
        videoPlayer.setVideoAllCallBack(null)
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
