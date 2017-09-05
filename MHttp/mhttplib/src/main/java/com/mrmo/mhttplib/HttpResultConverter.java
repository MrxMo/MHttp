package com.mrmo.mhttplib;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import rx.functions.Func1;

/**
 * json解析处理。注意：MHttpResultModel是统一处理json实体。根据实际情况自定该类。
 * Created by moguangjian on 2017/3/12.
 */

public class HttpResultConverter<T> implements Func1<String, T> {

    private static final String TAG = HttpResultConverter.class.getSimpleName();
    private Gson gson;

    public HttpResultConverter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public T call(String str) {
        T t = null;
        Type type = getClass().getGenericSuperclass();
        MHttpException mHttpException = new MHttpException();

        try {
            t = gson.fromJson(str, type);

        } catch (Exception e) {
            String msg = "解析异常";
            Log.e(TAG, msg);
            mHttpException.setCode(MHttpCode.M_HTTP_CODE_HANDLE_DATA_NULL);
            mHttpException.setMsg(msg);
            mHttpException.setDescription(msg);

            e.printStackTrace();

//            throw mHttpException;
        }

        // 接口状态判断处理
//        if (model != null && model.getStatus() != 200) {
//
//            mHttpException.setCode(model.getStatus());
//            mHttpException.setMsg(model.getMsg());
//            mHttpException.setDescription(model.getMsg());
//
//            throw mHttpException;
//        }
        return t;
    }
}
