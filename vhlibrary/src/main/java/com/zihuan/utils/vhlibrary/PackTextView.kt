package com.zihuan.utils.vhlibrary

import android.widget.TextView

class PackTextView {

    private var SpecialColor = 0
    private var GeneralColor = 0
    private var SpecialRes = 0
    private var GeneralRes = 0
    private var textType = DrawableType.NORMAL
    private var arrText: Array<out TextView>

    constructor(SpecialColor: Int, GeneralColor: Int, SpecialRes: Int, GeneralRes: Int, textType: DrawableType, arr: Array<out TextView>) {
        this.SpecialColor = SpecialColor
        this.GeneralColor = GeneralColor
        this.SpecialRes = SpecialRes
        this.GeneralRes = GeneralRes
        this.textType = textType
        this.arrText = arr
    }

    constructor(SpecialColor: Int, GeneralColor: Int, arr: Array<out TextView>) {
        this.SpecialColor = SpecialColor
        this.GeneralColor = GeneralColor
        this.arrText = arr
    }

    /**
     * 选中指定View色值
     */
    fun checkedColor(position: Int): PackTextView {
        if (SpecialColor != 0 && GeneralColor != 0)
            arrText.indexOfSpecial(SpecialColor, GeneralColor, position)
        return this
    }

    /**
     * 选中指定View的Drawable
     */
    fun checkedRes(position: Int): PackTextView {
        if (GeneralRes != 0 && SpecialRes != 0)
            arrText.forEachIndexed { index, textView ->
                val res = if (index != position) GeneralRes else SpecialRes
                textView.setDrawablesBounds(res, textType)
            }
        return this
    }

    /**
     * 同时选定color与drawable
     */
    fun checkedColorRes(position: Int): PackTextView {
        checkedColor(position)
        checkedRes(position)
        return this
    }

    /**
     * 获取TextView集合
     */
    fun getTextViews(): Array<out TextView> {
        return arrText
    }
}