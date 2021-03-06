package com.mrmo.mhttp.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrmo.mhttplib.MHttpCode;
import com.mrmo.mhttplib.MHttpException;

import java.lang.reflect.Type;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * json解析处理。注意：GHttpResultConverter。根据实际情况自定该类。
 * Created by moguangjian on 2017/3/12.
 */

public class GHttpResultConverter<T> implements Function<String, T> {

    private static final String TAG = GHttpResultConverter.class.getSimpleName();
    private Gson gson;

    public GHttpResultConverter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public T apply(@NonNull String str) throws Exception {
        T t = null;
        Type type = new TypeToken<T>(){}.getType();
        try {
            t = gson.fromJson(str, type);

        } catch (Exception e) {
            String msg = "解析异常";
            Log.e(TAG, msg);
            e.printStackTrace();

        }

        System.out.println(type.toString()+ " jj: "+t.toString());
//        if (model != null && !model.getStatus().equals("success")) {
//            mHttpException.setCode(-1);
//            mHttpException.setMsg(model.getStatus());
//            mHttpException.setDescription(model.getStatus());
//
//            throw mHttpException;
//        }


        return t;
    }
}
