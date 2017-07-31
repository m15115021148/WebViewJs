package com.sitemap.locationgather.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.sitemap.locationgather.R;
import com.sitemap.locationgather.util.ViewUtil;

import org.xutils.x;

/**
 * activity的基类
 * Created by chenM on 2017/7/17.
 */
public abstract class BaseActivity extends Activity {
    protected static final String TAG_ESC_ACTIVITY = "com.broader.esc";//内容描述 退出activity时 发送的广播信号
    private MyBroaderEsc receiver;//广播

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtil.initSystemBar(this, R.drawable.bg);
        // 设置无标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {//设置为竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        // 注册广播
        receiver = new MyBroaderEsc();
        registerReceiver(receiver, new IntentFilter(TAG_ESC_ACTIVITY));
        // 反射注解机制初始化
        x.view().inject(this);
        initData();
    }

    protected abstract void initData();//初始化数据

    /**
     * @发送广播 退出activity
     *
     */
    class MyBroaderEsc extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                finish();
            }
        }
    }

    /**
     * 回退事件
     *
     * @param v
     */
    public void onBack(View v) {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}
