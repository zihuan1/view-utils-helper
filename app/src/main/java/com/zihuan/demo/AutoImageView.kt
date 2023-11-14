package com.zihuan.demo

import android.content.Context
import android.graphics.Canvas
import android.graphics.LightingColorFilter
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView

class AutoImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val paint = Paint()

    override fun onDraw(canvas: Canvas) {
        if (drawable is BitmapDrawable) {
            val bitmap = (drawable as BitmapDrawable).bitmap
            paint.colorFilter = LightingColorFilter(0x000000, 0x000000)
            val pixel = bitmap.getPixel(0, 0)

            Log.e("当前色值", "$pixel")
        }
        super.onDraw(canvas)
    }
}