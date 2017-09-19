package com.mrmo.mhttplib;

import android.content.Context;

import com.mrmo.mhttplib.utils.MStringUtil;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.impl.client.BasicCookieStore;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * http请求。使用android-async-http请求http。
 * Created by moguangjian on 15/10/10 11:13.
 */
public class MHttpAsync implements MHttpAble {

    private static final String TAG = MHttpAsync.class.getSimpleName();
    //    private AsyncHttpClient asyncHttpClient; 
    protected Context context;
    private SyncHttpClient asyncHttpClient;

    public MHttpAsync(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        asyncHttpClient = new SyncHttpClient();
        asyncHttpClient.setTimeout(60 * 1000);
        asyncHttpClient.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        asyncHttpClient.setCookieStore(new BasicCookieStore());//new PersistentCookieStore(context);
        addHeader("Accept", "application/json;");
        addHeader("Connection", "keep-alive");
    }

    @Override
    public Object getInstance() {
        return asyncHttpClient;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void setTimeout(int second) {
        asyncHttpClient.setTimeout(second);
    }

    @Override
    public void addHeader(String header, String value) {
        if (header != null && value != value) {
            asyncHttpClient.addHeader(header, value);
        }
    }

    @Override
    public void removeHeader(String header) {
        asyncHttpClient.removeHeader(header);
    }

    @Override
    public void removeAllHeaders() {
        asyncHttpClient.removeAllHeaders();
    }

    @Override
    public <T> Observable<T> get(final String url, final Map<String, Object> params) {
        return request(url, params);
    }

    @Override
    public <T> Observable<T> post(String url, Map<String, Object> params) {
        return request(url, params);
    }

    @Override
    public <T> Observable<T> put(String url, Map<String, Object> params) {
        return request(url, params);
    }

    @Override
    public <T> Observable<T> delete(String url, Map<String, Object> params) {
        return request(url, params);
    }

    private Observable request(final String url, final Map<String, Object> params) {
        return  Observable.create(new Observable.OnSubscribe<Object>() {

            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                asyncHttpClient.post(url, mapToRequestParams(params), new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {

                        MHttpException mHttpException = new MHttpException();
                        mHttpException.setCode(statusCode);
                        mHttpException.setMsg("请求失败");
                        mHttpException.setDescription(throwable.toString());
                        subscriber.onError(mHttpException);

                        MOkHttp.printRequestStatusLog(TAG, getUrl(url, params), statusCode, response, false);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
                        subscriber.onNext(response);
                        subscriber.onCompleted();

                        MOkHttp.printRequestStatusLog(TAG, getUrl(url, params), statusCode, response, true);
                    }
                });
            }
        }).subscribeOn(Schedulers.io());
    }

    private String getUrl(String url, Map<String, Object> params) {
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append("?");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object object = entry.getValue();
            if (MStringUtil.isEmpty(key) || MStringUtil.isObjectNull(object)) {
                continue;
            }

            stringBuilder.append(key);
            stringBuilder.append("=");
            stringBuilder.append(object.toString());
            stringBuilder.append("&");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * map转RequestParams
     *
     * @param map
     * @return
     */
    public static RequestParams mapToRequestParams(Map<String, Object> map) {
        RequestParams params = new RequestParams();
//        params.setContentEncoding(HTTP.UTF_8);

        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    continue;
                }

                Object object = entry.getValue();
                if (object == null) {
                    object = "";
                }

                if (object instanceof File) {
                    params.put(key, (File) object, "image/jpeg", key);

                } else if (object instanceof File[]) {
//                    HQ项目需要这样用才可以上传成功
//                    params.put("file[]", (File[]) object, "image/jpeg", "file[]");
//                    String contentType = MHttpBridge.CONTENT_TYPE_IMAGE;
//                    if (map.containsKey(MHttpBridge.KEY_CONTENT_TYPE)) {
//                        contentType = (String) map.get(MHttpBridge.KEY_CONTENT_TYPE);
//                    }
//                    params.put(key, (File[]) object, contentType, key);
                    params.put(key, (File[]) object);

                } else {
                    String values = object.toString();
                    params.put(key, values);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return params;
    }
}
