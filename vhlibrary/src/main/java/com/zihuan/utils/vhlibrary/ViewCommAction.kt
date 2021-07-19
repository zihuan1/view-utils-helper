package com.zihuan.utils.vhlibrary

import android.animation.*
import android.app.Activity
import android.os.Handler
import android.os.Message
import android.view.View


/**
 * 一般情况下，所以的点击事件用这个
 *
 */
fun View.onClickPro(action: () -> Unit) {
    //不用onClick，isClickable方法在被禁用后无法再次启用
    setOnClickListener {
        if (clickFast()) return@setOnClickListener
        action()
    }
}

/**
 * 带缩放动画的事件
 */
fun View.onClickScale(action: () -> Unit) {
    onClickPro {
        scale2(1f, 1.1f, 1f) {
            action()
        }
    }
}

fun View.onClickScale2(action: () -> Unit) {
    onClickPro {
        scale2(1f, 1.02f, 1f) {
            action()
        }
    }
}

fun View.scale2(vararg scaleValue: Float, action: () -> Unit) {
    val sx = PropertyValuesHolder.ofFloat("scaleX", *scaleValue)
    val sy = PropertyValuesHolder.ofFloat("scaleY", *scaleValue)
    ObjectAnimator.ofPropertyValuesHolder(this, sx, sy)
        .apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    action()
                }
            })
            start()
        }
}

/**
 * 快速点击判定
 */
fun View.clickFast(): Boolean {
    val tag = getTag(R.id.tag_click_id)
    var exitTime = System.currentTimeMillis()
    if (tag != null && tag.toString().isNotEmpty()) {
        val time = tag.toString().toLong()
        if (exitTime - time < 1000) {
            return true
        }
    }
    setTag(R.id.tag_click_id, exitTime)
    return false
}

//点击间隔
private val DOUBLE_TIME: Long = 300

//上次点击时间
private var lastClickTime: Long = 0

/**
 * 是否是双击
 */
fun doubleClick(): Boolean {
    var currentTimeMillis = System.currentTimeMillis()
    if (currentTimeMillis - lastClickTime < DOUBLE_TIME && lastClickTime != 0L) {
        return true
    }
    lastClickTime = currentTimeMillis
    return false
}

fun View.objectAlpha(durations: Long = 1500): ObjectAnimator {
    return ObjectAnimator.ofFloat(this, "alpha", 1f, 0f)
        .apply {
            duration = durations
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
        }
}

fun View.aShow(durations: Long = 500): ObjectAnimator {
    return ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
        .apply {
            VShow()
            duration = durations
            start()
        }
}

fun View.aDismiss(durations: Long = 500): ObjectAnimator {
    return ObjectAnimator.ofFloat(this, "alpha", 1f, 0f)
        .apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                    VDismiss()
                }
            })
            duration = durations
            start()
        }
}

private val handle = object : Handler() {
    override fun handleMessage(msg: Message) {
        when (msg.what) {
            1 -> {
                val view = msg.obj as View
                (view.context as Activity).runOnUiThread {
                    view.aDismiss(msg.arg1.toLong())
                }
            }
        }
    }
}

fun View.aDismissDelay(durations: Long = 500, delay: Long = 500) {
    val msg = Message.obtain()
    msg.what = 1
    msg.arg1 = durations.toInt()
    msg.obj = this
    handle.sendEmptyMessageDelayed(1, delay)
}

fun View.aShow(durations: Long = 500, action: () -> Boolean) {
    if (action()) {
        aShow(durations)
    } else {
        aDismiss(durations)
    }
}

fun View.aDismiss(durations: Long = 500, action: () -> Boolean) {
    if (action()) {
        aDismiss(durations)
    } else {
        aShow(durations)
    }
}