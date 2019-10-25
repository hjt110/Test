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

        Map<String, String> hadMap = new HashMap<>();
        hadMap.put(UrlConfig.BASE_HEAD, UrlConfig.URL_A);
        RetrofitManager.init().executeGetWithHeader(hadMap, "api/v4/categories", new BaseObserver<List<TestBean>>() {
            @Override
            protected void onSuccess(List<TestBean> testBeans) {
                Log.e("success", testBeans + "");
            }
        });

        RetrofitManager.init()
                .executeGetUseUrl(new HashMap<>(), UrlConfig.URL_B + "banner/json", new HashMap<>(), new BaseObserver<Object>() {
                    @Override
                    protected void onSuccess(Object object) {

                    }
                });

//
//        RetrofitManager.init()
//                .getApiService()
//                .executeDownloadFile(new HashMap<>(), "https://d4975263df62a5727ed5f2e8637b3c74.dd.cdntips.com/imtt.dd.qq.com/16891/apk/965C257AF0F955BFEEEB006D72A6BE63.apk?mkey=5db297a37824c75e&f=1806&fsname=com.moji.mjweather_7.0932.02_7093202.apk&csr=1bbd&cip=120.36.225.171&proto=https", new HashMap<>())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<ResponseBody>() {
//                    @Override
//                    protected void onSuccess(ResponseBody responseBody) {
//                        boolean toDisk = new DownLoadUtils().writeResponseBodyToDisk(MainActivity.this, responseBody);
//                        if (toDisk) {
//                            System.out.println("下载成功请查看");
//                        } else {
//                            System.out.println("下载失败,请稍后重试");
//                        }
//                    }
//                });
    }
}
