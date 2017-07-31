package com.sitemap.locationgather.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.sitemap.locationgather.R;
import com.sitemap.locationgather.application.MyApplication;
import com.sitemap.locationgather.util.MacUtils;
import com.sitemap.locationgather.util.PreferencesUtil;
import com.sitemap.locationgather.util.ToastUtil;

import org.xutils.view.annotation.ContentView;

/**
 * 启动页面
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements Runnable {
    private SplashActivity mContext;//本类

    @Override
    protected void initData() {
        mContext = this;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//影藏系统状态栏
        MyApplication.diverID = MacUtils.getMacAddr();
        handler.postDelayed(this, 3000);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    MyApplication.diverID = MacUtils.getMacAddr();
                    if (!TextUtils.isEmpty(MyApplication.diverID)){
                        Intent intent = new Intent(mContext,MainActivity.class);
                        if (PreferencesUtil.getFirstLogin(mContext,"first"))
                            PreferencesUtil.isFirstLogin(mContext,"first",true);//第一次进入
                        startActivity(intent);
                    }else {
                        ToastUtil.showBottomShort(mContext,"获取mac地址失败");
                    }
                    mContext.finish();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 改写物理按键——返回的逻辑
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            handler.removeCallbacks(this);
            mContext.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(this);
        //强制回收
        System.gc();
    }

    @Override
    public void run() {
        handler.sendEmptyMessage(1);
    }

    @TargetApi(23)
    private void getPermission(Activity context) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {

        }
    }
}
