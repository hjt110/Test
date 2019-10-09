package com.tong.lib.net.ok3;

import com.tong.lib.utils.AppContextUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by hjt on 2019/10/9.
 */

public class Ok3 {

    private static final String HTTP_CACHE = "HttpCache";
    private static final long DEFAULT_HTTP_CACHE_LONG_SIZE = 1024 * 1024 * 100;//最大缓存大小
    private static final int CONNECT_TIMEOUT_TIME = 10; //连接超时时间
    private static final int READ_TIMEOUT_TIME = 10; //读取超时时间
    private static final int WRITE_TIMEOUT_TIME = 10; //写入超时间
    private static final int RETRY_COUNT = 0; //请求重连次数

    public OkHttpClient getOkHttpClient() {
        //缓存
        File cacheFile = new File(AppContextUtil.getContext().getCacheDir(), HTTP_CACHE);
        Cache cache = new Cache(cacheFile, DEFAULT_HTTP_CACHE_LONG_SIZE);
        return new OkHttpClient.Builder().connectTimeout(CONNECT_TIMEOUT_TIME, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(READ_TIMEOUT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(WRITE_TIMEOUT_TIME, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(InterceptorUtil.headerInterceptor())//增加头部信息
                .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                .retryOnConnectionFailure(true).readTimeout(RETRY_COUNT, TimeUnit.SECONDS)//请求失败重连次数
                .cache(cache).build();
    }
}
