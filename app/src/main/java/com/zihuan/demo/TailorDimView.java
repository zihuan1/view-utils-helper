package com.zihuan.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Logger;

@SuppressLint("AppCompatCustomView")
public class TailorDimView extends ImageView {


    private int Dst = 0;    //需要被裁剪什么形状  就传什么形状的图片   xml中使用
    private int Src = 0;   //源图片  xml中使用
    private Bitmap B_Dst;  //代码传入需要裁剪形状的图片
    private Bitmap B_Src;   //代码传入源图片
    private Integer transparency; //透明度  默认100(不透明)

    public TailorDimView(Context context) {
        this(context, null);
    }

    public TailorDimView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TailorDimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
        initBitmap();
    }

    public TailorDimView setBitmap_Dst(Bitmap bitmap_dst) {
        this.B_Dst = bitmap_dst;
        return this;
    }

    public TailorDimView setBitmap_Src(Bitmap bitmap_src) {
        this.B_Src = bitmap_src;
        return this;
    }

    public TailorDimView setTransparency(int l) {
        this.transparency = l;
        return this;
    }

    public void refresh() {
        initBitmap();
    }


    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TailorDimView, defStyleAttr, 0);
        //获取xml 中需要被裁剪形状的图片
        Dst = typedArray.getResourceId(R.styleable.TailorDimView_image_dst, 0);
        //获取xml 中源图片
        Src = typedArray.getResourceId(R.styleable.TailorDimView_image_src, 0);
        //透明度
        transparency = typedArray.getInteger(R.styleable.TailorDimView_trans, 100);
        typedArray.recycle();
    }


    private void initBitmap() {
        Bitmap bitmap_src = null;
        Bitmap bitmap_dst = null;
        //判断 有没有在代码中传入图片   优先级高于xml 设置
        if (null != B_Src && null != B_Dst) {
            bitmap_src = B_Src;
            bitmap_dst = B_Dst;
            //代码没有设置  则获取xml中的图片
        } else if (Dst != 0 && Src != 0) {
            bitmap_dst = BitmapFactory.decodeResource(getResources(), Dst);
            saveBitmap(bitmap_dst, "bitmap_dst");
            bitmap_src = zoomImg(BitmapFactory.decodeResource(getResources(), Src), 200, 200);
            saveBitmap(bitmap_src, "bitmap_src");
        } else {
            //都没有 则抛出异常(英语是语文老师教的- -)
            System.out.println("bitmap_src and bitmap_dst is nul");
            throw new NullPointerException();
        }
        //用于设置  Canvas 的位图
        Bitmap W_H = Bitmap.createBitmap(bitmap_dst.getWidth(), bitmap_dst.getHeight(), Bitmap.Config.ARGB_8888);

        //设置画布
        Canvas canvas = new Canvas(W_H);
        //设置画笔
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置 两张图片 覆盖的模式 (具体16中模式   百度---Canvas 覆盖模式)
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        //如果透明度 不是 100  则代码设置
        if (transparency != 100)
            paint.setAlpha(transparency);
        //设置源图
        canvas.drawBitmap(bitmap_src, 0, 0, null);
        //设置裁剪图片
        canvas.drawBitmap(bitmap_dst, 0, 0, paint);
        //置空
        paint.setXfermode(null);
        //设置显示图片  记住 一定是你设定的 V_H   之前我一直使用bitmap_dst  一直显示裁剪不成功  切记切记
        setImageBitmap(W_H);
        saveBitmap(W_H, "merge");
        saveBitmap(bitmap_dst, "bitmap_dst___!");
        saveBitmap(bitmap_src, "bitmap_src___!");
    }

    private Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        Bitmap newbm = null;
        if (bm != null) {
            // 获得图片的宽高
            int width = bm.getWidth();
            int height = bm.getHeight();
            // 计算缩放比例
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            // 取得想要缩放的matrix参数
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            // 得到新的图片
            newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        }
        return newbm;
    }

    public void saveBitmap(Bitmap bm, String picName) {
//
//        File file = new File("/sdcard/song/");
//        if(!file.exists())
//            file.mkdirs();
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath() + "/"+picName+".jpg");
//            bm.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
//            fileOutputStream.close();
//            System.out.println("saveBmp is here");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

}
