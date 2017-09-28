package com.mrmo.mhttplib;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.mrmo.mhttplib.utils.MStringUtil;
import com.google.gson.Gson;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by moguangjian on 15/10/14 17:51.
 */
public abstract class MAPI {

    private static boolean isDebug = true;

    private static final String TAG = MAPI.class.getSimpleName();

    protected Context context;
    private MHttpBridge mHttpBridge;
    protected Gson gson;

    public MAPI(Context context) {
        this.context = context;

        gson = new Gson();
        isDebug = getDebugStatus();
        initHttpBridge();
    }

    protected abstract String getApiServerPrefixDebug();

    protected abstract String getApiServerPrefix();

    protected abstract MHttpAble getMHttpAble();

    protected abstract boolean getDebugStatus();

    protected abstract boolean requestHttpPrepare(int method, String url, Map<String, Object> params);

    private void initHttpBridge() {
        MHttpAble mHttpAble = getMHttpAble();
        if (MStringUtil.isObjectNull(mHttpAble)) {
            mHttpAble = new MOkHttp(context);
        }

        mHttpBridge = new MHttpBridge();
        mHttpBridge.setMHttpAble(mHttpAble);
    }

    public static boolean isDebug() {
        return isDebug;
    }

    protected MHttpBridge getHttpBridge() {
        return mHttpBridge;
    }

    protected String getApiServer() {
        String apiServer;
        if (isDebug()) {
            apiServer = getApiServerPrefixDebug();
        } else {
            apiServer = getApiServerPrefix();
        }

        if (MStringUtil.isEmpty(apiServer)) {
            apiServer = "";
            Log.e(TAG, "api server address is null");
        }
        return apiServer;
    }

    protected <T> Observable<T> post(String url, Map<String, Object> params) {
        return requestHttp(MHttpAble.HTTP_METHOD_POST, url, params);
    }

    protected <T> Observable<T> get(String url, Map<String, Object> params) {
        return requestHttp(MHttpAble.HTTP_METHOD_GET, url, params);
    }

    protected <T> Observable<T> put(String url, Map<String, Object> params) {
        return requestHttp(MHttpAble.HTTP_METHOD_PUT, url, params);
    }

    protected <T> Observable<T> delete(String url, Map<String, Object> params) {
        return requestHttp(MHttpAble.HTTP_METHOD_DELETE, url, params);
    }

    private <T> Observable<T> requestHttp(int method, String url, Map<String, Object> params) {
        if (MStringUtil.isEmpty(url)) {
            url = "";
        }
        url = getApiServer() + url;
        boolean isRequest = requestHttpPrepare(method, url, params);

        Observable<T> observable = null;
        if (!isRequest) {
            return observable;
        }

        switch (method) {
            case MHttpAble.HTTP_METHOD_POST:
                observable = mHttpBridge.post(url, params);
                break;

            case MHttpAble.HTTP_METHOD_GET:
                observable = mHttpBridge.get(url, params);
                break;

            case MHttpAble.HTTP_METHOD_PUT:
                observable = mHttpBridge.put(url, params);
                break;

            case MHttpAble.HTTP_METHOD_DELETE:
                observable = mHttpBridge.delete(url, params);
                break;
        }

        return observable;
    }

    /**
     * 发送请求订阅
     *
     * @param observable
     * @param function
     * @param observer
     * @param <T>
     */
    @Deprecated
    protected <T> void requestSubscribe(Observable<T> observable, Func1 function, Observer observer) {
        observable
                .map(function)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    protected <T> void requestSubscribe(Observable<T> observable, Func1 function, final MObserver observer) {
        observable
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (observer instanceof MObserver) {
                            observer.onPrepare();
                        }

                    }
                })
                .map(function)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
