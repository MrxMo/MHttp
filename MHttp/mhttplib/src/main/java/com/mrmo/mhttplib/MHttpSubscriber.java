package com.mrmo.mhttplib;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 数据转发类。将数据回调到接口MHttpResponseAble中。
 * Created by moguangjian on 2017/3/12.
 */

public class MHttpSubscriber<T> implements Observer<T> {

    private Context context;
    private MHttpResponseAble mHttpResponseAble;

    private boolean isShowProgress = true;

    public MHttpSubscriber(Context context, MHttpResponseAble mHttpResponseAble) {
        init(context, mHttpResponseAble, true);
    }

    public MHttpSubscriber(Context context, MHttpResponseAble mHttpResponseAble, boolean isShowProgress) {
        init(context, mHttpResponseAble, isShowProgress);
    }

    private void init(Context context, MHttpResponseAble mHttpResponseAble, boolean isShowProgress) {
        this.context = context;
        this.mHttpResponseAble = mHttpResponseAble;
        this.isShowProgress = isShowProgress;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (mHttpResponseAble == null) {
            return;
        }

        if (isShowProgress && context instanceof MActivityProgressAble) {
            ((MActivityProgressAble) context).showProgress();
        }

        mHttpResponseAble.onPrepare();
    }

    @Override
    public void onNext(T t) {
        if (mHttpResponseAble == null) {
            return;
        }

//        if (t == null) {
//            mHttpResponseAble.onFailure(context, MHttpCode.M_HTTP_CODE_HANDLE_DATA_NULL, "数据为空");
//            return;
//        }

        mHttpResponseAble.onSuccess(200, t);
        mHttpResponseAble.onFinish();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();

        String msg = "未知异常";
        int code = MHttpCode.M_HTTP_CODE_UNKNOWN;

        if (mHttpResponseAble == null) {
            return;
        }

        if (e instanceof MHttpException) {
            msg = ((MHttpException) e).getMsg();
        }

        mHttpResponseAble.onFailure(context, code, msg);

        if (isShowProgress && context instanceof MActivityProgressAble) {
            ((MActivityProgressAble) context).hideProgress();
        }

        mHttpResponseAble.onFinish();
    }

    @Override
    public void onComplete() {
        if (mHttpResponseAble == null) {
            return;
        }

        mHttpResponseAble.onFinish();

        if (isShowProgress && context instanceof MActivityProgressAble) {
            ((MActivityProgressAble) context).hideProgress();
        }
    }
}
