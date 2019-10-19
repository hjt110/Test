package com.tong.lib.net.ok3;

import com.tong.lib.net.retrofit.api.UrlConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hjt on 2019/10/18.
 */

public class BaseUrlInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        // 获取request
        Request request = chain.request();
        // 从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();
        // 获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        // 从request中获取headers，通过给定的键url_name
        List<String> headerValues = request.headers(UrlConfig.BASE_HEAD);
        if (headerValues != null && headerValues.size() > 0) {
            // 如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader(UrlConfig.BASE_HEAD);
            // 匹配获得新的BaseUrl
            String headerValue = headerValues.get(0);
            HttpUrl newBaseUrl = null;
            if (headerValue.equals(UrlConfig.URL_A)) {
                newBaseUrl = HttpUrl.parse(UrlConfig.URL_A);
            } else if (headerValue.equals(UrlConfig.URL_B)) {
                newBaseUrl = HttpUrl.parse(UrlConfig.URL_B);
            } else {
                newBaseUrl = oldHttpUrl;
            }
            // 重建新的HttpUrl，修改需要修改的url部分
            HttpUrl newFullUrl = oldHttpUrl.newBuilder()
                    // 更换网络协议
                    .scheme(newBaseUrl.scheme())
                    // 更换主机名
                    .host(newBaseUrl.host())
                    // 更换端口
                    .port(newBaseUrl.port())
                    .build();
            // 重建这个request，通过builder.url(newFullUrl).build()；
            // 然后返回一个response至此结束修改
            return chain.proceed(builder.url(newFullUrl).build());
        }
        return chain.proceed(request);
    }
}
