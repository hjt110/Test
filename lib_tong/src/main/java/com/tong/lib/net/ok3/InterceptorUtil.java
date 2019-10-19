package com.tong.lib.net.ok3;


import android.util.Log;

import com.tong.lib.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by hjt on 2019/4/23.
 */

public class InterceptorUtil {

    private static String TAG = "retrofit";

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(message -> {
            if (BuildConfig.DEBUG) Log.i(TAG + "——>", message);
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

    /**
     * 在拦截器加通用请求头
     *
     * @return
     */
    public static Interceptor headerInterceptor() {
        return chain -> {
            Request request = chain.request();
            //这边添加
//            Request build = request.newBuilder().addHeader("Content-Type", "application/json;charset=UTF-8").build();
            return chain.proceed(request);
        };
    }
}
