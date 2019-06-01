package com.wangxingxing.download.ui.module.about

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.IntentUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.wangxingxing.download.BuildConfig
import com.wangxingxing.download.Constants
import com.wangxingxing.download.R
import com.wangxingxing.download.RouterManager
import kotlinx.android.synthetic.main.activity_about.*

@Route(path = RouterManager.URL_ABOUT)
class AboutActivity : AppCompatActivity() {

    val GOOGLE_PLAY = "com.android.vending"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        BarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimaryDark))

        toolbar.title = getString(R.string.menu_about)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvName.text = getString(R.string.app_name)
        tvVersion.text = "v${BuildConfig.VERSION_NAME}"
        btnGrade.setOnClickListener {
            openGooglePlay()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openGooglePlay() {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("market://details?id=$packageName")
            intent.setPackage(GOOGLE_PLAY)
            if (IntentUtils.isIntentAvailable(intent)) {
                //有google play, 打开
                startActivity(intent)
            } else {
                //没有google play, 用浏览器访问
                val intentBrowser = Intent(Intent.ACTION_VIEW)
                intentBrowser.data = Uri.parse(Constants.GOOGLE_PLAY_URL)
                if (IntentUtils.isIntentAvailable(intentBrowser)) {
                    startActivity(intentBrowser)
                } else {
                    ToastUtils.showShort(getString(R.string.toast_open_app_error))
                }
            }
        } catch (e: ActivityNotFoundException) {
            LogUtils.e(e.toString())
        }
    }
}
