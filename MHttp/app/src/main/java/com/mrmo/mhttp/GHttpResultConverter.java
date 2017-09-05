package com.mrmo.mhttp;

import android.util.Log;

import com.google.gson.Gson;
import com.mrmo.mhttp.net.HttpResultModel;
import com.mrmo.mhttplib.MHttpCode;
import com.mrmo.mhttplib.MHttpException;

import rx.functions.Func1;

/**
 * json解析处理。注意：GHttpResultConverter。根据实际情况自定该类。
 * Created by moguangjian on 2017/3/12.
 */

public class GHttpResultConverter<T> implements Func1<String, T> {

    private static final String TAG = GHttpResultConverter.class.getSimpleName();
    private Gson gson;

    public GHttpResultConverter(Gson gson) {
        this.gson = gson;
    }


    @Override
    public T call(String str) {
        T t = null;
        HttpResultModel model = null;
        try {
            model = gson.fromJson(str, HttpResultModel.class);
            t = (T) model;

        } catch (Exception e) {
            String msg = "解析异常";
            Log.e(TAG, msg);
            MHttpException mHttpException = new MHttpException();
            mHttpException.setCode(MHttpCode.M_HTTP_CODE_HANDLE_DATA_NULL);
            mHttpException.setMsg(msg);
            mHttpException.setDescription(msg);

            e.printStackTrace();

//            throw mHttpException;
        }


        if (model != null && model.getStatus() != 1) {
            MHttpException mHttpException = new MHttpException();
            mHttpException.setCode(model.getStatus());
            mHttpException.setMsg(model.getMsg());
            mHttpException.setDescription(model.getMsg());

//            throw mHttpException;
        }


        return t;
    }
}
