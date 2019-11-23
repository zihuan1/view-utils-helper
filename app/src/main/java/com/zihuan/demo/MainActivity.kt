package com.zihuan.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zihuan.utils.vhlibrary.VShow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_1.VShow()
    }
}
