package com.example.administrator.test;

import android.util.Log;

import com.example.administrator.test.base.BaseActivity;
import com.tong.lib.net.retrofit.RetrofitManager;
import com.tong.lib.net.retrofit.api.UrlConfig;
import com.tong.lib.net.retrofit.observer.BaseObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        Map<String,String> hadMap = new HashMap<>();
        hadMap.put(UrlConfig.BASE_HEAD,UrlConfig.URL_A);
        RetrofitManager.init().executeGetWithHeader(hadMap,"api/v4/categories", new BaseObserver<List<TestBean>>() {
            @Override
            protected void onSuccess(List<TestBean> testBeans) {
                Log.e("success", testBeans + "");
            }
        });

//        Map<String,String> headMap = new HashMap<>();
//        headMap.put(UrlConfig.BASE_HEAD,UrlConfig.URL_B);
//        RetrofitManager.init().executeGetWithHeader(headMap,"banner/json", new BaseObserver<Object>() {
//            @Override
//            protected void onSuccess(Object object) {
//                Log.e("sss",object.toString());
//            }
//        });

        RetrofitManager.init().executeGetUseUrl(new HashMap<>(), UrlConfig.URL_B + "banner/json", new HashMap<>(), new BaseObserver<Object>() {
            @Override
            protected void onSuccess(Object object) {

            }
        });
    }

}
