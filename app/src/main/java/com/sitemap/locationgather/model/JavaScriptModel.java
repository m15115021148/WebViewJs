package com.sitemap.locationgather.model;

import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.sitemap.locationgather.view.CustomWebView;

/**
 * Created by chenMeng on 2017/7/27.
 */

public class JavaScriptModel {
    private CustomWebView mWb;
    private String mUrl;//加载地址
    private Handler mHandler = new Handler();

    public JavaScriptModel(CustomWebView webView,String url){
        this.mWb = webView;
        this.mUrl = url;
    }

    /**
     * 刷新
     */
    @JavascriptInterface
    public void refresh(){
        // 通过handler来确保init方法的执行在handler绑定的Activity的主线程中
        mHandler.post(new Runnable() {

            public void run() {
                mWb.loadUrl(mUrl);
                Log.w("jack", mUrl);
            }
        });
    }

    /**
     * 404错误页面
     */
    @JavascriptInterface
    public void error(){
        // 通过handler来确保init方法的执行在handler绑定的Activity的主线程中
        mHandler.post(new Runnable() {

            public void run() {
                mWb.loadUrl("file:///android_asset/error.html");
            }
        });
    }
}
