package com.wangxingxing.download.ui.module.main

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.wangxingxing.download.BaseApplication
import com.wangxingxing.download.R
import com.wangxingxing.download.RouterManager
import com.wangxingxing.download.utils.DownloadUtils
import kotlinx.android.synthetic.main.content_main.*
import kotlin.random.Random

@Route(path = RouterManager.URL_MAIN)
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val titles = arrayOf(
        BaseApplication.instance.getString(R.string.tab_title_downloading),
        BaseApplication.instance.getString(R.string.tab_title_completed)
    )

    /**
     * 王者荣耀
     * 微信
     * QQ
     * 知乎
     * 和平精英
     * 淘宝
     */
    private val testUrls = arrayOf(
        "https://2557605746df7b3dceccbb2d933c5a3e.dd.cdntips.com/imtt.dd.qq.com/16891/560EF9A52643144E8E95F656E89794DB.apk?mkey=5cf0b3357151e2a8&f=1849&fsname=com.tencent.tmgp.sgame_1.44.1.19_44011902.apk&csr=1bbd&cip=113.81.196.93&proto=https",
        "https://bc365e9352cb2837e74d5b99b1a4f158.dd.cdntips.com/imtt.dd.qq.com/16891/B188C34E3E46CE136B67733D71E3FB8D.apk?mkey=5cf0bd7e7151e2a8&f=0ce9&fsname=com.tencent.mm_7.0.4_1420.apk&csr=1bbd&cip=113.81.196.93&proto=https",
        "https://5d3356b38586e30a7c58fec462f93770.dd.cdntips.com/imtt.dd.qq.com/16891/3516E694B3909797027536EE93177DC3.apk?mkey=5cf0bac47151e2a8&f=0c58&fsname=com.tencent.mobileqq_8.0.5_1186.apk&csr=1bbd&cip=113.81.196.93&proto=https",
        "https://bc365e9352cb2837e74d5b99b1a4f158.dd.cdntips.com/imtt.dd.qq.com/16891/C28DF3447E996C93B9D97261A14AE0FE.apk?mkey=5cf0ba937151e2a8&f=1026&fsname=com.zhihu.android_5.45.0_1266.apk&csr=1bbd&cip=113.81.196.93&proto=https",
        "https://a4035eacbeadddcf369c81b2d63e684d.dd.cdntips.com/imtt.dd.qq.com/16891/461FE40B65C07681776B618EC6D73B07.apk?mkey=5cf0ba6d7151e2a8&f=1806&fsname=com.tencent.tmgp.pubgmhd_1.1.16_8000.apk&csr=1bbd&cip=113.81.196.93&proto=https",
        "https://bf45a1d0861cf7963d7797cd2532fd4c.dd.cdntips.com/imtt.dd.qq.com/16891/A648B2CCD07D1444DEB8A1F629B4F18F.apk?mkey=5cf0ba207151e2a8&f=0c2f&fsname=com.taobao.taobao_8.8.0_243.apk&csr=1bbd&cip=113.81.196.93&proto=https"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            DownloadUtils.download(testUrls[Random.nextInt(6)])
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        setupViewPager()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupViewPager() {
        val adapter = PageAdapter(supportFragmentManager)
        adapter.addFragment(DownloadingPlusFragment.newInstance(), titles[0])
        adapter.addFragment(DownloadedFragment.newInstance(), titles[1])

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        viewPager.offscreenPageLimit = 2
        viewPager.currentItem = 0
    }
}
