package com.example.administrator.test.base;

import android.app.Application;

import com.tong.lib.net.retrofit.RetrofitManager;
import com.tong.lib.utils.AppContextUtil;

import cn.jpush.android.api.JPushInterface;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);  // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);  // 初始化 JPush
        AppContextUtil.init(this);
        RetrofitManager.setBaseUrl("https://baobab.kaiyanapp.com/api/");
    }
}
