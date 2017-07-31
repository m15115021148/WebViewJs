package com.sitemap.locationgather.application;

import android.app.Application;
import android.util.DisplayMetrics;

import com.sitemap.locationgather.util.NetworkUtil;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by chenMeng on 2017/7/17.
 */

public class MyApplication extends Application {
    private static MyApplication instance;//application对象
    public static NetworkUtil netState;//网络状态
    public static String diverID = "";//设备id
    public static int screenWidth = 0;
    public static int screenHeight = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        initXutils();
        netState = new NetworkUtil(getApplicationContext());
        getScreenSize();
    }

    private static MyApplication instance() {
        if (instance != null) {
            return instance;
        } else {
            return new MyApplication();
        }
    }

    /**
     * 获取屏幕尺寸
     */
    private void getScreenSize() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenHeight = dm.heightPixels;
        screenWidth = dm.widthPixels;
    }

    /**
     * 初始化xutils框架
     */
    private void initXutils() {
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }

    /**
     * 获取手机网络状态对象
     *
     * @return
     */
    public static NetworkUtil getNetObject() {
        if (netState != null) {
            return netState;
        } else {
            return new NetworkUtil(instance().getApplicationContext());
        }
    }
}
