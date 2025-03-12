package com.zihuan.utils.vhlibrary

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.*

/**
 *
 * @Description
 * @author zihuan
 * @date 2019/10/23 16:59
 */

/**
 * 将图片转换成指定颜色
 */
fun transformImageColor(color: Int): PorterDuffColorFilter {
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


/**
 * 显示当前view
 */
fun <T : View> T.VShow() = apply { visibility = View.VISIBLE }


/**隐藏view**/
fun <T : View> T.VInvisible() = apply { visibility = View.INVISIBLE }

fun <T : View> T.VDismiss() = apply { visibility = View.GONE }

fun VShow(vararg views: View) = views.ViewForEach { View.VISIBLE }

fun VInvisible(vararg views: View) = views.ViewForEach { View.INVISIBLE }

fun VDismiss(vararg views: View) = views.ViewForEach { View.GONE }

fun VShow(vararg views: View, action: () -> Boolean) = views.ViewForEach {
    if (action()) View.VISIBLE else View.GONE
}

fun VDismiss(vararg views: View, action: () -> Boolean) = VShow(*views) {
    !action()
}

/**
 * 将显示状态反转
 */
fun View.convert() {
    convert(this)
}

fun convert(vararg views: View) {
    views.forEach { it.VDismiss { it.VIsShow() } }
}

fun Array<out View>.ViewForEach(action: () -> Int) = apply {
    forEach {
        it.visibility = action()
    }
}


/***
 * 传入一段lambda表达式来判断是否显示当前view
 */
fun <T : View> T.VShow(term: T.() -> Boolean) = if (term()) VShow() else VDismiss()

fun <T : View> T.VInvisible(term: T.() -> Boolean) = if (term()) VShow() else VInvisible()

fun <T : View> T.VDismiss(term: () -> Boolean) = if (term()) VDismiss() else VShow()

//当前状态是否是显示
fun View.VIsShow() = (visibility == View.VISIBLE)

fun View.VIsInvisible() = (visibility == View.INVISIBLE)

//当前状态是否隐藏
fun View.VIsDismiss() = (visibility == View.GONE)

/**TextView选择字体颜色**/
fun <T : TextView> T.VColor(color: Int, color2: Int, action: T.() -> Boolean) =
        apply { setTextColor(context.resources.getColor(if (action()) color else color2)) }

fun <T : TextView> T.VColor(color: Int, color2: Int, action: Boolean) =
        apply { setTextColor(context.resources.getColor(if (action) color else color2)) }

fun <T : View> T.VBackGroundColor(color: Int, color2: Int, action: T.() -> Boolean) =
        apply { background = context.resources.getDrawable(if (action()) color else color2) }

fun <T : View> T.VBackGroundColor(color: Int, color2: Int, action: Boolean) =
        apply { background = context.resources.getDrawable(if (action) color else color2) }

//
fun Array<out View>.indexOfShow(index: Int) {
    get(index).VShow()
}

fun Array<out View>.indexOfInvisible(index: Int) {
    get(index).VInvisible()
}

fun Array<out View>.indexOfDismiss(index: Int) {
    get(index).VDismiss()
}

fun Array<out View>.firstShow() {
    indexOfShow(0)
}

fun Array<out View>.firstInvisible() {
    indexOfInvisible(0)
}

fun Array<out View>.firstDismiss() {
    indexOfDismiss(0)
}

fun Array<out View>.lastShow() {
    indexOfShow(lastIndex)
}

fun Array<out View>.lastInvisible() {
    indexOfInvisible(lastIndex)
}

fun Array<out View>.lastDismiss() {
    indexOfDismiss(lastIndex)
}


/**多个TextView颜色操作**/
fun VTextViews(vararg textView: TextView) = textView

@Deprecated("用下面修复命名的方法")
fun TextView.setDrawablesBounds(start: Int = 0, tip: Int = 0, end: Int = 0, bottom: Int = 0) {
    setDrawableBounds(start, tip, end, bottom)
}
/**TextView设置DrawablesBound**/
fun TextView.setDrawableBounds(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0): TextView {
    setCompoundDrawablesRelativeWithIntrinsicBounds(left, top, right, bottom)
    return this
}

fun TextView.setDrawablesBounds(res: Int, type: DrawableType): TextView {
    var left = if (DrawableType.LEFT == type) res else 0
    var top = if (DrawableType.TOP == type) res else 0
    var right = if (DrawableType.RIGHT == type) res else 0
    var bottom = if (DrawableType.BOTTOM == type) res else 0
    setDrawableBounds(left, top, right, bottom)
    return this
}

fun ProgressBar.setProgressDrawables(res: Int, res2: Int, action: () -> Boolean): ProgressBar {
    val draw = if (action()) res else res2
    progressDrawable = resources.getDrawable(draw)
    return this
}


fun SeekBar.setProgressDrawables(res: Int, res2: Int, action: () -> Boolean): SeekBar {
    val draw = if (action()) res else res2
    progressDrawable = resources.getDrawable(draw)
    return this
}

fun SeekBar.setThumb(res: Int, res2: Int, action: () -> Boolean): SeekBar {
    val draw = if (action()) res else res2
    thumb = resources.getDrawable(draw)
    return this
}


enum class DrawableType {
    NORMAL, LEFT, TOP, RIGHT, BOTTOM
}

/**
 * @param specialColor 特殊的颜色
 * @param generalColor 普通的颜色
 */
fun <T : TextView> Array<out T>.firstSpecial(specialColor: Int, generalColor: Int) =
        apply {
            indexOfSpecial(specialColor, generalColor, 0)
        }

fun <T : TextView> Array<out T>.lastSpecial(specialColor: Int, generalColor: Int) =
        apply {
            indexOfSpecial(specialColor, generalColor, lastIndex)
        }

fun <T : TextView> Array<out T>.indexOfSpecial(specialColor: Int, generalColor: Int, position: Int) =
        apply {
            forEachIndexed { index, textView ->
                textView.VColor(specialColor, generalColor, position == index)
            }
        }


/**
 * 包装多个TextView的Color和Drawable,并改变当前选中的View状态
 */
fun <T : TextView> Array<out T>.packText(specialColor: Int, generalColor: Int, specialRes: Int, generalRes: Int, drawableType: DrawableType) =
        PackTextView(specialColor, generalColor, specialRes, generalRes, drawableType, this)

/**
 * 包装多个TextView的Color,并改变当前选中的TextView状态
 */
fun <T : TextView> Array<out T>.packText(specialColor: Int, generalColor: Int) =
        PackTextView(specialColor, generalColor, this)


/**
 * 根据条件选择图片
 */
fun <T : ImageView> T.imageSelector(resPositive: Int, resNegative: Int, action: () -> Boolean) =
        apply {
            setImageResource(if (action()) resPositive else resNegative)
        }

fun <T : ImageView> T.imageSelectors(vararg res: Int, action: (res: Array<Int>) -> Int) =
        apply {
            setImageResource(action(res.toTypedArray()))
        }

/**
 * 光标移动到指定位置,默认为最后
 */
fun <T : EditText> T.selection(index: Int = this.text.length): T {
    setSelection(index)
    return this
}

fun <T : TextView> T.VTextResource(resPositive: Int, resNegative: Int, action: () -> Boolean) =
        if (action()) resPositive else resNegative
