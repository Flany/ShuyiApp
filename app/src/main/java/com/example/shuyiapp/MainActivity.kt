package com.example.shuyiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.news.mvm.NewsFragment
import com.example.shuyiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val newsFragment = NewsFragment.launch()
        supportFragmentManager.beginTransaction().add(R.id.container, newsFragment).commit()
    }
}