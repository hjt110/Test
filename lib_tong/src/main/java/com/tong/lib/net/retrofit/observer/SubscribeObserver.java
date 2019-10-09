package com.tong.lib.net.retrofit.observer;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * 创建时间:2019/9/27
 * 创建人：anthony.wang
 * 功能描述：
 */
public class SubscribeObserver<T> implements Observer<ResponseBody> {
    private BaseObserver<T> baseObserver;
    public SubscribeObserver(BaseObserver<T> baseObserver){
        this.baseObserver = baseObserver;
    }
    @Override
    public void onSubscribe(Disposable d) {
        baseObserver.onSubscribe(d);
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            String json = responseBody.string();
            baseObserver.onNext(baseObserver.getEntityData(json));
        } catch (Exception e) {
            baseObserver.onError(e);
        }
    }

    @Override
    public void onError(Throwable e) {
        baseObserver.onError(e);
    }

    @Override
    public void onComplete() {
        baseObserver.onComplete();
    }
}
