package com.zihuan.utils.vhlibrary

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes

/**
 * 在指定行数最后面添加省略号
 * @param line 目标行数
 * @param dotSpace 为省略号留出几个字符的空间,一般情况下一个字符足以盛下省略号
 * 在recyclerView中使用会有问题,目前只能提前处理
 */
fun ellipsis(textView: TextView, line: Int, dotSpace: Int = 1): String {
    if (textView.lineCount <= line) return textView.text.toString()
    val lineEndIndex = textView.layout.getLineEnd(line) //目标行数
    val cutText = "${textView.text.subSequence(0, lineEndIndex - dotSpace)}…"//1个字符可以盛下省略号
    return cutText
}

fun TextView.ellipsis(line: Int) {
    post {
        text = ellipsis(this, line)
    }
}


fun imageSelect(target: Int, @DrawableRes vararg arg: Int): Int {
    arg.forEachIndexed { index, res ->
        if (target == index) {
            return res
        }
    }
    return arg.first()
}

fun View.vMarginLeft(left: Int) {
    vMargin(left = left)
}

fun View.vMarginTop(top: Int) {
    vMargin(top = top)
}

fun View.vMarginRight(right: Int) {
    vMargin(right = right)
}

fun View.vMarginBottom(bottom: Int) {
    vMargin(bottom = bottom)
}

fun View.vMarginHorizontal(margin: Int) {
    vMargin(left = margin, right = margin)
}

fun View.vMarginVertical(margin: Int) {
    vMargin(top = margin, bottom = margin)
}

fun View.vMargin(left: Int = -1, top: Int = -1, right: Int = -1, bottom: Int = -1) {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    if (left != -1) {
        params.leftMargin = left
    }
    if (top != -1) {
        params.topMargin = top
    }
    if (right != -1) {
        params.rightMargin = right
    }
    if (bottom != -1) {
        params.bottomMargin = bottom
    }
    layoutParams = params
}

fun View.setMatchParent() {
    setParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
}

fun View.setParams(width: Int = ViewGroup.LayoutParams.WRAP_CONTENT, height: Int = ViewGroup.LayoutParams.WRAP_CONTENT) {
    val params = layoutParams
    params.width = width
    params.height = height
    layoutParams = params
}