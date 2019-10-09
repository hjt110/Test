package com.example.administrator.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.administrator.test.base.BaseActivity;
import com.tong.lib.net.retrofit.RetrofitManager;
import com.tong.lib.net.retrofit.observer.BaseObserver;
import com.tong.lib.net.retrofit.observer.SubscribeObserver;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity {


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        RetrofitManager.init().executeGet("v4/categories", new BaseObserver<List<TestBean>>() {
            @Override
            protected void onSuccess(List<TestBean> testBeans) throws Exception {
                Log.e("success",testBeans+"");
            }
        });
    }

}
