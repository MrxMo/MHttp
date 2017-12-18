package com.mrmo.mhttplib;

import android.content.Context;

import com.mrmo.mhttplib.config.HttpErrorHintAble;
import com.mrmo.mhttplib.config.MHttpConfig;
import com.mrmo.mhttplib.utils.MToast;

/**
 * http数据回调实现类
 * Created by moguangjian on 15/10/10 16:01.
 */
public abstract class MHttpResponseImpl<T> implements MHttpResponseAble<T> {

    public abstract void onSuccessResult(int statusCode, T t);

    @Override
    public void onFailure(Context context, int statusCode, Object object) {
        String msg = "未知异常";
        if (object instanceof String) {
            msg = object.toString();
        }

        HttpErrorHintAble httpErrorHintAble = MHttpConfig.getInstance().getHttpErrorHintAble();
        if (httpErrorHintAble != null) {

            httpErrorHintAble.onShow(context, statusCode, msg);

            if (!httpErrorHintAble.isShowDefaultHint()) {
                return;
            }
        }

        if (context == null) {
            return;
        }
        MToast.show(context, msg);
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
