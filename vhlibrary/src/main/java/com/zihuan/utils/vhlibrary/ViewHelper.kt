package com.zihuan.utils.vhlibrary

import android.view.View
import android.widget.TextView

/**
 *
 * @Description 简化了view一些操作
 * @author zihuan
 * @date 2019/10/23 16:59
 */


/***
 * 滑动改变色值
 *  scrollY 滑动的距离
 *  height 标题栏的高度
 *  return 透明度
 */
fun viewAlphaChange(scrollY: Int, height: Int): Int {
    return when (scrollY) {
        in 0..height -> { //滑动过程中 并且在height之内
            val scale = scrollY.toFloat() / height
            val alpha = 255 * scale
            alpha.toInt()
        }
        else -> 255
    }
}

/**
 * 给TextView设置图片
 */
fun TextView.setDrawablesBounds(start: Int = 0, tip: Int = 0, end: Int = 0, bottom: Int = 0) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(start, tip, end, bottom)
}

/**
 * 隐藏view
 */
fun View.VDismiss() {
    visibility = View.GONE
}

/**
 * 隐藏多个view
 */
fun VDismiss(vararg views: View): Array<View> {
    views.forEach {
        it.visibility = View.GONE
    }
    return views as Array<View>
}

fun View.VDismiss(term: () -> Boolean) {
    if (term()) VDismiss() else VShow()
}

/**
 * 显示当前view
 */
fun View.VShow() {
    visibility = View.VISIBLE
}

/**
 * 显示多个view
 */
fun VShow(vararg views: View): Array<View> {
    views.forEach {
        it.visibility = View.VISIBLE
    }
    return views as Array<View>
}

/***
 * 传入一段lambda表达式来判断是否显示当前view
 */
fun View.VShow(term: () -> Boolean) {
    if (term()) VShow() else VDismiss()
}


fun Array<View>.indexOfShow(index: Int) {
    get(index).VShow()
}

fun Array<View>.indexOfDismiss(index: Int) {
    get(index).VDismiss()
}

fun Array<View>.firstShow() {
    indexOfShow(0)
}

fun Array<View>.firstDismiss() {
    indexOfDismiss(0)
}

fun Array<View>.lastShow() {
    indexOfShow(lastIndex)
}

fun Array<View>.lastDismiss() {
    indexOfDismiss(lastIndex)
}