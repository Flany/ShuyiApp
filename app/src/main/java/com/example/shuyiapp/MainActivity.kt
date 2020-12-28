package com.example.shuyiapp

import android.view.Menu
import android.view.MenuItem
import com.android.scan.ScanActivity
import com.android.scan.plugin.ScanConfig
import com.android.scan.plugin.ScanFactory
import com.example.base.BaseActivity
import com.example.base.utils.ToastUtils
import com.example.news.mvm.NewsFragment
import com.example.shuyiapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var backPressTime = 0L

    override fun initViews() {
        val scan = ScanFactory.create(ScanConfig.scanType)
        scan.registerScan(this)
        lifecycle.addObserver(scan)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, NewsFragment(), null).commit()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getStatName(): String {
        return javaClass.name
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_scan -> {
                ScanActivity.launch(this)
            }
        }
        return true
    }

    override fun onBackPressed() {
        val now = System.currentTimeMillis()
        if (now - backPressTime > 2000) {
            ToastUtils.toast("再按一次退出")
            backPressTime = now
        } else {
            super.onBackPressed()
        }
    }
}