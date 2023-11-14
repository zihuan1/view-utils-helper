//package com.zihuan.demo;
//
//import android.os.Bundle;
//import android.view.Window;
//import android.widget.TextView;
//import android.widget.Toolbar;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class ColorActivity extends AppCompatActivity {
//
//    Toolbar mToolbar;
//    TextView mTitleTv, mColorTv1, mColorTv6;
//    StringBuffer strBuffer = new StringBuffer();
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_color);
//
//        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
//        mTitleTv = (TextView) this.findViewById(R.id.tv_toolbar_title);
//        mTitleTv.setText("Color 色值判断");
//        mColorTv1 = (TextView) this.findViewById(R.id.color_tv1);
//        mColorTv6 = (TextView) this.findViewById(R.id.color_tv6);
//
//        strBuffer.append("R.color.test_color1 方式  " + R.color.test_color1 + "\n" + "R.color.test_color2 方式  " + R.color.test_color2 + "\n");
//        strBuffer.append("getResources().getColor(R.color.test_color1) 方式  " + getResources().getColor(R.color.test_color1) + "\n" + "getResources().getColor(R.color.test_color2) 方式  " + getResources().getColor(R.color.test_color2) + "\n" + "getResources().getColor(R.color.test_color3) 方式  " + getResources().getColor(R.color.test_color3) + "\n");
//
//        if (mToolbar.getBackground() instanceof ColorDrawable) {
//            ColorDrawable colordDrawable = (ColorDrawable) mToolbar.getBackground();
//            int color = colordDrawable.getColor();
//
//            Log.e("=======color1==" + color, "=======color2==" + getResources().getColor(R.color.test_color3));
//        }
//
//        if (mColorTv1.getBackground() instanceof ColorDrawable) {
//            ColorDrawable colordDrawable = (ColorDrawable) mColorTv1.getBackground();
//            int color = colordDrawable.getColor();
//            strBuffer.append("ColorTv  " + color + "\n");
//            strBuffer.append("ColorTv 十六进制  " + Color_16(color) + "\n");
//            strBuffer.append("ColorTv 十六进制(无透明度)  " + Color_16_NoAlpha(color) + "\n");
//            strBuffer.append("ColorTv 透明度 " + Color_Alpha(color) + " 红 " + Color_Red(color) + " 绿 " + Color_Green(color) + " 蓝 " + Color_Blue(color) + "\n");
//        }
//        strBuffer.append("R.color.test_color3 十六进制  " + Color_16(getResources().getColor(R.color.test_color3)) + "\n");
//        strBuffer.append("R.color.test_color3 十六进制(无透明度)  " + Color_16_NoAlpha(getResources().getColor(R.color.test_color3)) + "\n");
//        strBuffer.append("R.color.test_color3 透明度 " + Color_Alpha(getResources().getColor(R.color.test_color3)) + " 红 " + Color_Red(getResources().getColor(R.color.test_color3)) + " 绿 " + Color_Green(getResources().getColor(R.color.test_color3)) + " 蓝 " + Color_Blue(getResources().getColor(R.color.test_color3)) + "\n");
//        strBuffer.append("R.color.test_color4 十六进制  " + Color_16(getResources().getColor(R.color.test_color4)) + "\n");
//        strBuffer.append("R.color.test_color4 十六进制(无透明度)  " + Color_16_NoAlpha(getResources().getColor(R.color.test_color4)) + "\n");
//        strBuffer.append("R.color.test_color4 透明度 " + Color_Alpha(getResources().getColor(R.color.test_color4)) + " 红 " + Color_Red(getResources().getColor(R.color.test_color4)) + " 绿 " + Color_Green(getResources().getColor(R.color.test_color4)) + " 蓝 " + Color_Blue(getResources().getColor(R.color.test_color4)) + "\n");
//        mColorTv6.setText(strBuffer.toString());
//
//    }
//
//    String Color_16(int color) {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("#");
//        stringBuffer.append(Integer.toHexString(Color.alpha(color)));
//        stringBuffer.append(Integer.toHexString(Color.red(color)));
//        stringBuffer.append(Integer.toHexString(Color.green(color)));
//        stringBuffer.append(Integer.toHexString(Color.blue(color)));
//        return stringBuffer.toString();
//    }
//
//    String Color_16_NoAlpha(int color) {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("#");
//        stringBuffer.append(Integer.toHexString(Color.red(color)));
//        stringBuffer.append(Integer.toHexString(Color.green(color)));
//        stringBuffer.append(Integer.toHexString(Color.blue(color)));
//        return stringBuffer.toString();
//    }
//
//    String Color_Alpha(int color) {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(Integer.toHexString(Color.alpha(color)));
//        return stringBuffer.toString();
//    }
//
//    String Color_Red(int color) {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(Integer.toHexString(Color.red(color)));
//        return stringBuffer.toString();
//    }
//
//    String Color_Green(int color) {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(Integer.toHexString(Color.green(color)));
//        return stringBuffer.toString();
//    }
//
//    String Color_Blue(int color) {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(Integer.toHexString(Color.blue(color)));
//        return stringBuffer.toString();
//    }
//
//    public static int Color_ChangeAlpha(int color, int alpha) {
//        int red = Color.red(color);
//        int green = Color.green(color);
//        int blue = Color.blue(color);
//
//        return Color.argb(alpha, red, green, blue);
//    }
//}