package com.zihuan.utils.vhlibrary

import android.animation.*
import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment


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
        action()
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

fun View.alphaShow(durations: Long = 500): ObjectAnimator {
    return ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
        .apply {
            VShow()
            duration = durations
            start()
        }
}

fun View.alphaDismiss(durations: Long = 500): ObjectAnimator {
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

fun View.alphaShow(durations: Long = 500, action: () -> Boolean) {
    if (action()) {
        alphaShow(durations)
    } else {
        alphaDismiss(durations)
    }
}

fun View.alphaDismiss(durations: Long = 500, action: () -> Boolean) {
    if (action()) {
        alphaDismiss(durations)
    } else {
        alphaShow(durations)
    }
}