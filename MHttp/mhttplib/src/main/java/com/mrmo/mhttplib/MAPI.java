package com.mrmo.mhttplib;

import android.content.Context;
import android.util.Log;

import com.mrmo.mhttplib.utils.MStringUtil;

import java.util.Map;

import static com.mrmo.mhttplib.MHttpAble.HTTP_METHOD_DELETE;
import static com.mrmo.mhttplib.MHttpAble.HTTP_METHOD_GET;
import static com.mrmo.mhttplib.MHttpAble.HTTP_METHOD_POST;
import static com.mrmo.mhttplib.MHttpAble.HTTP_METHOD_PUT;

/**
 * Created by moguangjian on 15/10/14 17:51.
 */
public abstract class MAPI {

    private static final String TAG = MAPI.class.getSimpleName();

    protected Context context;
    private MHttpBridge mHttpBridge;

    public MAPI(Context context) {
        this.context = context;

        initHttpBridge();
    }

    protected abstract String getApiServerPrefixDebug();

    protected abstract String getApiServerPrefix();

    protected abstract MHttpAble getMHttpAble();

    protected abstract boolean isDebug();

    protected abstract boolean requestHttpPrepare(int method, String url, Map<String, Object> params, MHttpResponseAble mHttpResponseAble);

    private void initHttpBridge() {
        MHttpAble mHttpAble = getMHttpAble();
        if (MStringUtil.isObjectNull(mHttpAble)) {
            mHttpAble = new MOkHttp(context);
        }

        mHttpBridge = new MHttpBridge();
        mHttpBridge.setMHttpAble(mHttpAble);
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

    protected void post(String url, Map<String, Object> params, MHttpResponseAble mHttpResponseAble) {
        requestHttp(MHttpAble.HTTP_METHOD_POST, url, params, mHttpResponseAble);
    }

    protected void get(String url, Map<String, Object> params, MHttpResponseAble mHttpResponseAble) {
        requestHttp(MHttpAble.HTTP_METHOD_GET, url, params, mHttpResponseAble);
    }

    protected void put(String url, Map<String, Object> params, MHttpResponseAble mHttpResponseAble) {
        requestHttp(MHttpAble.HTTP_METHOD_PUT, url, params, mHttpResponseAble);
    }

    protected void delete(String url, Map<String, Object> params, MHttpResponseAble mHttpResponseAble) {
        requestHttp(MHttpAble.HTTP_METHOD_DELETE, url, params, mHttpResponseAble);
    }

    private void requestHttp(int method, String url, Map<String, Object> params, MHttpResponseAble mHttpResponseAble) {
        if (MStringUtil.isEmpty(url)) {
            url = "";
        }
        url = getApiServer() + url;
        boolean isRequest = requestHttpPrepare(method, url, params, mHttpResponseAble);

        if (!isRequest) {
            return;
        }

        switch (method) {
            case HTTP_METHOD_POST:
                mHttpBridge.post(url, params, mHttpResponseAble);
                break;

            case HTTP_METHOD_GET:
                mHttpBridge.get(url, params, mHttpResponseAble);
                break;

            case HTTP_METHOD_PUT:
                mHttpBridge.put(url, params, mHttpResponseAble);
                break;

            case HTTP_METHOD_DELETE:
                mHttpBridge.delete(url, params, mHttpResponseAble);
                break;
        }
    }

}
