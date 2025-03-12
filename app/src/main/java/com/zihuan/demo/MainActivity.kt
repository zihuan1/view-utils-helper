package com.zihuan.demo

import android.app.Activity
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.PixelCopy
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.zihuan.utils.vhlibrary.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Field
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        tv_1.VShow()
        convert(tvTest1, tvTest2)
//        tv_1.VInvisible()
        tv_1.VColor(R.color.colorAccent, R.color.colorPrimary, true)
//        VInvisible(tv_1, tv_2).lastShow()
//        VShow(tv_1, tv_2).lastDismiss()
//        VDismiss(tv_1, tv_2).lastShow()
        VTextViews(tv_1, tv_2).lastSpecial(R.color.colorAccent, R.color.colorPrimary)
//        iv_select.imageSelector(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round) { true }
        iv_select2.imageSelector(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round) { false }
        iv_select.imageSelectors(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round) {
            return@imageSelectors it[0]
        }
        tv_2.setOnClickListener {
            var a = tv_3.text.toString()
//            a.isEmptyExtend {
//                Log.e("输出1", "测试$a")
//                return@setOnClickListener
//            }
            Log.e("输出2", "测试$a")
        }

        val textClass = VTextViews(tvTest1, tvTest2, tvTest3).packText(
            R.color.colorAccent, R.color.colorPrimary,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, DrawableType.RIGHT
        )
        tvTest1.setOnClickListener {
            textClass.checkedColorRes(0)
        }
        tvTest2.setOnClickListener {
            textClass.checkedColorRes(1)
        }
        tvTest3.setOnClickListener {
            textClass.checkedColorRes(2)
        }
        progressBar.setProgressDrawables(R.drawable.bg_progress, R.drawable.bg_seekbar) { false }
        seekBar.setProgressDrawables(R.drawable.bg_progress, R.drawable.bg_seekbar) { false }
        seekBar.setThumb(R.drawable.head_test, R.drawable.ic_psych_cb_3) { true }
        tvTestColor.setOnClickListener {
            randomColor { index, color ->
                Log.e("随机色值", "位置 $index   $color")
            }
            val color = randomColor()
            tvTestColor.setTextColor(resources.getColor(color))
        }

//        var list = ArrayList<String>()
//        (0..100).forEach {
//            list.add("$it")
//        }
//        zrv_test.buildVerticalLayout(ReAdapter()).setData(list)
//
//        var test = Child()
//        val mClass = test.javaClass
////        var superObj = Child().javaClass
//        var superClass = mClass.newInstance().javaClass.superclass
//        var field = superClass.getDeclaredField("mStr")
//        //取消语法访问检查
//        field.isAccessible = true
////        var inputMore = field.get(superClass)
//        field.set(superClass, "222")
//        Log.e("反射", mClass.toString())
//
//        var ff = getFiledsInfo("com.zihuan.demo.Child")
//        ff.forEach {
//            Log.e("反射", it.name)
//            it.isAccessible = true
//            var a = it.get("mList")
//        }

//        val decimal = "0.012345".keepTwoDecimal()
//        Log.e("小数", decimal)
        Log.e("小数2", String.format("%.2f", (0.123456f)))
        Log.e("小数3", String.format("%.2f", (0.123456)))
//        setImageInfo()
//        val bitmap=BitmapFactory.decodeResource(resources,R.drawable.head_test)
//        val bitmap2=BitmapFactory.decodeResource(resources,R.mipmap.test1)
//        tailorDimView.setBitmap_Dst(bitmap)
//        tailorDimView.setBitmap_Src(bitmap2)
    }

    fun getFiledsInfo(className: String): List<Field> {
        val list = ArrayList<Field>()
        val clazz = Class.forName(className)
        val superClazz = clazz.superclass
        if (superClazz != null) {
            val superFields = superClazz.declaredFields
            list.addAll(listOf<Field>(*superFields))
        }
        return list
    }

    fun <T> getFiledsInfo(clazz: Class<T>): List<Field> {
        val list = ArrayList<Field>()
        val superClazz = clazz.superclass
        if (superClazz != null) {
            val superFields = superClazz.declaredFields
            list.addAll(superFields)
        }
        return list
    }


    private fun setImageInfo() {
        ivHeadTest.post {
            val bitmap = getBitmapFromView(this, ivHeadTest)
            val pixel = bitmap.getPixel(0, 0)
            ivHeadTest3.setBackgroundColor(pixel)
            val bitmapWidth = bitmap.width
            val bitmapHeight = bitmap.height
            val left = ivHeadTest.left
            val top = ivHeadTest.top

            (0..bitmapWidth).forEach {
//                if (bitmap.getPixel(it, 0) == -328966) {
//                    Log.e("透明图", "非透明图")
//                } else {
//
//                }
            }
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//               val a= ColorSpace.get(ColorSpace.Named.SRGB)
//            }
            Log.e(
                "当前色值",
                "$pixel ${Color_16(pixel)} bitmapWidth$bitmapWidth bitmapHeight$bitmapHeight left$left top$top"
            )
        }
    }

    fun Color_16(color: Int): String? {
        val stringBuffer = StringBuffer()
        stringBuffer.append("#")
        stringBuffer.append(Integer.toHexString(Color.alpha(color)))
        stringBuffer.append(Integer.toHexString(Color.red(color)))
        stringBuffer.append(Integer.toHexString(Color.green(color)))
        stringBuffer.append(Integer.toHexString(Color.blue(color)))
        return stringBuffer.toString()
    }

    fun Color_16_NoAlpha(color: Int): String? {
        val stringBuffer = StringBuffer()
        stringBuffer.append("#")
        stringBuffer.append(Integer.toHexString(Color.red(color)))
        stringBuffer.append(Integer.toHexString(Color.green(color)))
        stringBuffer.append(Integer.toHexString(Color.blue(color)))
        return stringBuffer.toString()
    }

    /**
     * 从 View 中获取 Bitmap
     *
     * @param activity Build.VERSION.SDK_INT >= Build.VERSION_CODES.O 会使用 activity获取 window
     * @param view     将要被截图的View
     * @return view的截图
     */
    private fun getBitmapFromView(activity: Activity, view: View): Bitmap {
        val bitmap: Bitmap
        //请求转换
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //获取layout的位置
            val location = IntArray(2)
            view.getLocationInWindow(location)
            //准备一个bitmap对象，用来将copy出来的区域绘制到此对象中
            bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888, true)
            PixelCopy.request(
                activity.window,
                Rect(location[0], location[1], location[0] + view.width, location[1] + view.height),
                bitmap, { copyResult ->
                    //如果成功
                    if (copyResult === PixelCopy.SUCCESS) {
                        //这里做的回调,其实这个过程可以作为同步代码处理
                        ivHeadTest2.setImageBitmap(bitmap)
                        getBitmapOffset(ivHeadTest, true)
                    }
                }, Handler(Looper.getMainLooper())
            )
        } else {
            view.setDrawingCacheEnabled(true)
            view.buildDrawingCache()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                view.measure(
                    View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(view.getHeight(), View.MeasureSpec.EXACTLY)
                )
                view.layout(
                    view.getX() as Int,
                    view.getY() as Int,
                    view.getX() as Int + view.getMeasuredWidth(),
                    view.getY() as Int + view.getMeasuredWidth()
                )
            } else {
                view.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                )
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight())
            }
            bitmap = Bitmap.createBitmap(
                view.getDrawingCache(),
                0, 0, view.getMeasuredWidth(), view.getMeasuredHeight()
            )
            view.setDrawingCacheEnabled(false)
            view.destroyDrawingCache()
        }
        return bitmap
    }

    /**
     * 获取Bitmap在ImageView中的偏移量数组,其中第0个值表示在水平方向上的偏移值,第1个值表示在垂直方向上的偏移值
     *
     * @param imageView
     * @param includeLayout 在计算偏移的时候是否要考虑到布局的因素,如果要考虑该因素则为true,否则为false
     * @return the offsets of the bitmap inside the imageview, offset[0] means horizontal offset, offset[1] means vertical offset
     */
    private fun getBitmapOffset(imageView: ImageView, includeLayout: Boolean): IntArray {
        val offset = IntArray(2)
        val values = FloatArray(9)
        val matrix: Matrix = imageView.imageMatrix
        matrix.getValues(values)
        // x方向上的偏移量(单位px)
        offset[0] = values[2].toInt()
        // y方向上的偏移量(单位px)
        offset[1] = values[5].toInt()
        if (includeLayout) {
            val params = imageView.layoutParams as MarginLayoutParams
            val paddingTop: Int = imageView.paddingTop
            val paddingLeft: Int = imageView.paddingLeft
            offset[0] += paddingLeft + params.leftMargin
            offset[1] += paddingTop + params.topMargin
        }
        Log.e("矩阵信息", "${offset[0]}")
        Log.e("矩阵信息", "${offset[1]}")
        return offset
    }
}
