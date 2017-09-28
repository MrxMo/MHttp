package com.mrmo.mhttplib;

import android.content.Context;

import rx.Observer;


/**
 * 数据转发类。将数据回调到接口MHttpResponseAble中。
 * Created by moguangjian on 2017/3/12.
 */

public class MHttpSubscriber<T> implements MObserver<T> {

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
    public void onPrepare() {
        if (mHttpResponseAble == null) {
            return;
        }
        mHttpResponseAble.onPrepare();

        if (isShowProgress && context instanceof MActivityProgressAble) {
            ((MActivityProgressAble) context).showProgress();
            ((MActivityProgressAble) context).addRequestRecordCount();
        }

    }

    @Override
    public void onNext(T t) {
        if (mHttpResponseAble == null) {
            return;
        }

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
        mHttpResponseAble.onFinish();

        if (context instanceof MActivityProgressAble) {
            MActivityProgressAble mActivityProgressAble = ((MActivityProgressAble) context);
            if (isShowProgress) {
                mActivityProgressAble.reduceRequestRecordCount();
            }

            if (mActivityProgressAble.isRequestAllFinish()) {
                mActivityProgressAble.hideProgress();
            }
        }
    }

    @Override
    public void onCompleted() {
        if (mHttpResponseAble == null) {
            return;
        }
        mHttpResponseAble.onFinish();

        if (context instanceof MActivityProgressAble) {
            MActivityProgressAble mActivityProgressAble = ((MActivityProgressAble) context);
            if (isShowProgress) {
                mActivityProgressAble.reduceRequestRecordCount();
            }

            if (mActivityProgressAble.isRequestAllFinish()) {
                mActivityProgressAble.hideProgress();
            }
        }


    }
}
