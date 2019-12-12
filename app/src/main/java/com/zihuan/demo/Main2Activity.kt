package com.zihuan.demo

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log

import java.lang.reflect.Field

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        testSuperField()
    }

    fun testSuperField() {
        try {
            val aa = Child()
            val mClass = Child::class.java
            val field = mClass.superclass!!.getDeclaredField("mStr")
            field.isAccessible = true
            val a = field.get(aa) as String
            Log.e("反射", a)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
