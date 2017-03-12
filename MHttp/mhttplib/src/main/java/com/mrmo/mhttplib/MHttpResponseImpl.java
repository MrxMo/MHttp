package com.mrmo.mhttplib;

import android.content.Context;
import android.widget.Toast;

/**
 * http数据回调实现类
 * Created by moguangjian on 15/10/10 16:01.
 */
public abstract class MHttpResponseImpl<T> implements MHttpResponseAble<T> {

    public abstract void onSuccessResult(int statusCode, T t);

    @Override
    public void onFailure(Context context, int statusCode, Object object) {
        if (context == null) {
            return;
        }

        String msg = "未知异常";
        if (object instanceof String) {
//            MToastUtil.show(context, object.toString());
            msg = object.toString();
        }

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(int statusCode, T t) {
        onSuccessResult(statusCode, t);
    }

    @Override
    public void onFinish() {
    }

    @Override
    public void onProgress(long bytesWritten, long totalSize) {

    }

    @Override
    public void onPrepare() {

    }

}
