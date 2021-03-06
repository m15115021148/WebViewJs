package com.sitemap.locationgather.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sitemap.locationgather.R;
import com.sitemap.locationgather.application.MyApplication;
import com.sitemap.locationgather.model.JavaScriptModel;
import com.sitemap.locationgather.util.PreferencesUtil;
import com.sitemap.locationgather.view.CustomWebView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private MainActivity mContext;//本来
    @ViewInject(R.id.webView)
    private CustomWebView mWb;//webview
    private String url;//路径
    private long exitTime = 0;//退出的时间

    @Override
    protected void initData() {
        mContext = this;
        url = "http://218.202.235.66:6060/IndoorFingerprintGather?mac=" + MyApplication.diverID;
        if (PreferencesUtil.getFirstLogin(mContext, "first")) {
            url = url + "&first=1";//第一安装app
            PreferencesUtil.isFirstLogin(mContext, "first", false);
        } else {
            url = url + "&first=0";
        }
        Log.w("jack", url);
        mWb.loadUrl(url);
        mWb.addJavascriptInterface(new JavaScriptModel(mWb,url), "android");
        mWb.setWebListener(new CustomWebView.OnWebViewListener() {
            @Override
            public void onProgressChanged(int progress) {
            }

            @Override
            public void onError() {
                Log.e("result", "onError:");
                String data = "";
                mWb.loadUrl("javascript:document.body.innerHTML=\"" + data + "\"");
                mWb.loadUrl("file:///android_asset/error.html");
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 退出activity
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if (mWb.canGoBack()) {
//                mWb.goBack();// 返回上一页面
//                return true;
//            } else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(this, "再按一次退出程序!",
                            Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    //退出所有的activity
                    Intent intent = new Intent();
                    intent.setAction(BaseActivity.TAG_ESC_ACTIVITY);
                    sendBroadcast(intent);
                    finish();
                }
//            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
