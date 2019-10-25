package com.tong.lib.net.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tong.lib.net.ok3.Ok3;
import com.tong.lib.net.retrofit.api.ApiService;
import com.tong.lib.net.retrofit.observer.BaseObserver;
import com.tong.lib.net.retrofit.observer.SubscribeObserver;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hjt on 2019/10/9.
 */

public class RetrofitManager {

    private static RetrofitManager mRetrofitManager;
    private static String BASE_URL = "";
    private final ApiService mApiService;
    private static final String HTTP_MEDIA_TYPE = "application/json ; charset=utf-8";
    private static Gson mGson = new GsonBuilder().disableHtmlEscaping().create();

    private RetrofitManager() {
        Retrofit build = new Retrofit.Builder().client(new Ok3().getOkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .build();
        mApiService = build.create(ApiService.class);
    }

    public static RetrofitManager init() {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofitManager;
    }

    public ApiService getApiService(){
        return mApiService;
    }

    public static void setBaseUrl(String baseUrl) {
        if (BASE_URL.equals("")) BASE_URL = baseUrl;
    }

    public <T> Observable executeGet(String url, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executeGet(url), baseObserver);
    }

    public <T> Observable executeGet(String url, Map<String, String> map, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executeGet(url, map), baseObserver);
    }

    public <T> Observable executeGet(String url, RequestBody requestBody, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executeGet(url, requestBody), baseObserver);
    }

    public <T> Observable executePost(String url, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executePost(url), baseObserver);
    }

    public <T> Observable executePost(String url, Map<String, String> map, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executePost(url, map), baseObserver);
    }

    public <T> Observable executePost(String url, RequestBody requestBody, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executePost(url, requestBody), baseObserver);
    }

    public <T> Observable executeGetWithHeader(Map<String, String> headMap, String url, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executeGetWithHeader(headMap, url), baseObserver);
    }

    public <T> Observable executeGetWithHeader(Map<String, String> headMap, String url, Map<String, String> map, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executeGetWithHeader(headMap, url, map), baseObserver);
    }

    public <T> Observable executeGetWithHeader(Map<String, String> headMap, String url, RequestBody requestBody, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executeGetWithHeader(headMap, url, requestBody), baseObserver);
    }

    public <T> Observable executePostWithHeader(Map<String, String> headMap, String url, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executePostWithHeader(headMap, url), baseObserver);
    }

    public <T> Observable executePostWithHeader(Map<String, String> headMap, String url, Map<String, String> map, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executePostWithHeader(headMap, url, map), baseObserver);
    }

    public <T> Observable executePostWithHeader(Map<String, String> headMap, String url, RequestBody requestBody, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executePostWithHeader(headMap, url, requestBody), baseObserver);
    }

    public <T> Observable executeGetUseUrl(Map<String, String> headMap, String url, Map<String, String> map, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executeGetUseUrl(headMap, url, map), baseObserver);
    }

    public <T> Observable executePostUseUrl(Map<String, String> headMap, String url, Map<String, String> map, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executePostUseUrl(headMap, url, map), baseObserver);
    }

    public <T> Observable executePostUseUrl(Map<String, String> headMap, String url, RequestBody requestBody, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executePostUseUrl(headMap, url, requestBody), baseObserver);
    }

    public <T> Observable executePostFile(Map<String, String> headMap, String url, MultipartBody.Part part, BaseObserver<T> baseObserver) {
        return setThread(mApiService.executePostFile(headMap, url, part), baseObserver);
    }

    private <T> Observable setThread(Observable<ResponseBody> requestObservable, BaseObserver<T> baseObserver) {
        requestObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SubscribeObserver<>(baseObserver));
        return requestObservable;
    }

    public static RequestBody getRequestBody(Object object) {
        RequestBody requestBody = RequestBody.create(MediaType.parse(HTTP_MEDIA_TYPE), mGson.toJson(object));
        return requestBody;
    }

}
