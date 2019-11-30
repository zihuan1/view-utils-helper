package com.zihuan.utils.vhlibrary

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.TextView

/**
 *
 * @Description
 * @author zihuan
 * @date 2019/10/23 16:59
 */

/**
 * 将图片转换成指定颜色
 */
fun TransformImageColor(color: Int): PorterDuffColorFilter {
    return PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}

/***
 *  scrollY 滑动的距离
 *  height 标题栏的高度
 *  return 透明度
 */
fun viewAlphaChange(scrollY: Int, height: Int): Int {
    return when (scrollY) {
        in 0..height -> { //滑动过程中 并且在height之内
            val scale = scrollY.toFloat() / height
            val alpha = 255 * scale
//                Logger.tag("滑动中 scale $scale alpha $alpha")
            alpha.toInt()
        }
        else -> 255
    }
}

fun TextView.setDrawablesBounds(start: Int = 0, tip: Int = 0, end: Int = 0, bottom: Int = 0) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(start, tip, end, bottom)
}


//隐藏view
fun <T : View> T.VDismiss(): T {
    visibility = View.GONE
    return this
}

fun VDismiss(vararg views: View): Array<View> {
    views.forEach {
        it.visibility = View.GONE
    }
    return views as Array<View>
}

fun <T : View> T.VDismiss(term: () -> Boolean) = if (term()) VDismiss() else VShow()

/**
 * 显示当前view
 */
fun <T : View> T.VShow(): T {
    visibility = View.VISIBLE
    return this
}

fun VShow(vararg views: View): Array<View> {
    views.forEach {
        it.visibility = View.VISIBLE
    }
    return views as Array<View>
}

/***
 * 传入一段lambda表达式来判断是否显示当前view
 */
fun <T : View> T.VShow(term: T.() -> Boolean) = if (term()) VShow() else VDismiss()

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

//当前状态是否是显示
fun View.VIsShow() = (visibility == View.VISIBLE)

//当前状态是否隐藏
fun View.VIsDismiss() = (visibility == View.GONE)