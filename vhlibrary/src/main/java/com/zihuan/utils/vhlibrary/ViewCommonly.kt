package com.zihuan.utils.vhlibrary

import android.net.http.SslError
import android.os.Build
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * View一般设置
 */
fun WebView.commonly(): WebView {
    val webSettings: WebSettings = settings
    //设置自适应屏幕，两者合用
    webSettings.useWideViewPort = true //将图片调整到适合webview的大小
    webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小
//        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //关闭webview中缓存
    webSettings.allowFileAccess = true //设置可以访问文件
    webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
    webSettings.loadsImagesAutomatically = true //支持自动加载图片
    webSettings.defaultTextEncodingName = "utf-8" //设置编码格式
    webSettings.javaScriptEnabled = true;
    webSettings.cacheMode = WebSettings.LOAD_NO_CACHE;
    webSettings.domStorageEnabled = true;
    webSettings.databaseEnabled = true;
    webSettings.setAppCacheEnabled(true);
    webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS;
    webSettings.useWideViewPort = true;
    setBackgroundColor(0); // 设置背景色
    scrollBarSize = 0
//    background.alpha = 0; // 设置填充透明度 范围：0-255
    background = resources.getDrawable(android.R.color.transparent)
    webViewClient = object : WebViewClient() {
        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError?) {
            // 不要使用super，否则有些手机访问不了，因为包含了一条 handler.cancel()
            // super.onReceivedSslError(view, handler, error);
            // 接受所有网站的证书，忽略SSL错误，执行访问网页
            handler.proceed()
        }
    }
    return this
}