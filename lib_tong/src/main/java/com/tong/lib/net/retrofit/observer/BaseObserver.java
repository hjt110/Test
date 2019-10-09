package com.tong.lib.net.retrofit.observer;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tong.lib.net.retrofit.BaseResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by hjt on 2019/10/9.
 */

public abstract class BaseObserver<T> implements Observer<T> {

    private final String TYPE_CLASS_ENTITY_HEAD = "class";
    private final String TYPE_LIST_ENTITY_HEAD = "java.util.List";
    private final String TYPE_ARRAY_LIST_ENTITY_HEAD = "java.util.ArrayList";

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        try {
            onSuccess(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e("网络请求错误", e.toString() + "");
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(T t) throws Exception;

    /**
     * 这里是处理Json转换成实例对象或者集合的方法
     */
    public T getEntityData(String json) {
        Gson gson = new Gson();
        T entityData = null;
        ParameterizedType parameterizedType = ((ParameterizedType) getClass().getGenericSuperclass());
        Type type = parameterizedType.getActualTypeArguments()[0];
        if (type.toString().startsWith(TYPE_CLASS_ENTITY_HEAD)) {//首先判断是否是对象形式的data
            Class<T> tClass = (Class<T>) type;
            entityData = gson.fromJson(json, tClass);
        } else if (type.toString().startsWith(TYPE_LIST_ENTITY_HEAD) || type.toString()
                .startsWith(TYPE_ARRAY_LIST_ENTITY_HEAD))
        {//如果是集合形式的data会走这里的异常 直接转集合就行了
            entityData = gson.fromJson(json, TypeToken.get(type).getType());
        }
        return entityData;
    }
}
