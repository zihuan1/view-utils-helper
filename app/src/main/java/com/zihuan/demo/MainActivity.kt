package com.zihuan.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zihuan.utils.vhlibrary.VColor
import com.zihuan.utils.vhlibrary.VInvisible
import com.zihuan.utils.vhlibrary.VShow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_1.VShow()
//        tv_1.VInvisible()
        tv_1.VColor(R.color.colorAccent, R.color.colorPrimary, true)
        var list = ArrayList<String>()
        (0..100).forEach {
            list.add("$it")
        }
        zrv_test.buildVerticalLayout(ReAdapter()).setData(list)
    }
}
