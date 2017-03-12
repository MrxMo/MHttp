package com.mrmo.mhttplib;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.mrmo.mhttplib.utils.MStringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.internal.schedulers.IoScheduler;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * http请求。使用OkHttp请求http。
 * Created by moguangjian on 2017/3/6.
 */

public class MOkHttp implements MHttpAble {

    private static final String TAG = MOkHttp.class.getSimpleName();
    protected Context context;
    private static Gson gson;
    private static OkHttpClient okHttpClient;
    private Map headerHashMap;

    public MOkHttp(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        headerHashMap = new HashMap();
    }

    @Override
    public Object getInstance() {

        return getHttpInstance();
    }

    private synchronized OkHttpClient getHttpInstance() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
//                    .cookieJar(new CookieJar() {
//                        @Override
//                        public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
//                            cookieStore.put(httpUrl.host(), list);
//                        }
//
//                        @Override
//                        public List<Cookie> loadForRequest(HttpUrl httpUrl) {
//                            List<Cookie> cookies = cookieStore.get(httpUrl.host());
//                            return cookies != null ? cookies : new ArrayList<Cookie>();
//                        }
//                    })
                    .build();
        }
        return okHttpClient;
    }

    private synchronized Gson getGsonInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    @Override
    public void destroy() {
        gson = null;
        okHttpClient = null;
    }

    @Override
    public void setTimeout(int second) {
//        getHttpInstance().setConnectTimeout(30, TimeUnit.SECONDS);
    }

    @Override
    public void addHeader(String header, String value) {
        if (MStringUtil.isEmpty(header) || MStringUtil.isEmpty(value)) {
            Log.w(TAG, "header or value is null .");
            return;
        }

        headerHashMap.put(header, value);
    }

    @Override
    public void removeHeader(String header) {
        if (MStringUtil.isEmpty(header)) {
            Log.w(TAG, "header is null .");
            return;
        }

        headerHashMap.remove(header);
    }

    @Override
    public void removeAllHeaders() {
        headerHashMap.clear();
    }

    @Override
    public <T> Observable<T> get(String url, Map<String, Object> params) {
        Request request = getRequest(MHttpAble.HTTP_METHOD_GET, url, params);
        return request(params, request);
    }


    @Override
    public <T> Observable<T> post(String url, Map<String, Object> params) {
        Request request = getRequest(MHttpAble.HTTP_METHOD_POST, url, params);
        return request(params, request);
    }

    @Override
    public <T> Observable<T> put(String url, Map<String, Object> params) {
        Request request = getRequest(MHttpAble.HTTP_METHOD_PUT, url, params);
        return request(params, request);
    }

    @Override
    public <T> Observable<T> delete(String url, Map<String, Object> params) {
        Request request = getRequest(MHttpAble.HTTP_METHOD_DELETE, url, params);
        return request(params, request);
    }

    private Request getRequest(int methodType, String url, Map<String, Object> param) {
        Request.Builder builder = new Request.Builder();
        addHeader(builder);
        addParams(methodType, url, builder, param);

        return builder.build();
    }

    private void addHeader(Request.Builder builder) {
        builder.header("User-Agent", "OkHttp Headers.java");
        builder.addHeader("Accept", "application/json; q=0.5");
        builder.addHeader("Accept", "application/vnd.github.v3+json");

        Set<Map.Entry<String, String>> set = headerHashMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
    }

    private void addParams(int methodType, String url, Request.Builder builder, Map<String, Object> param) {
        if (MStringUtil.isEmpty(url)) {
            url = "";
        }

        Object[] params = getParams(param);

        switch (methodType) {
            case HTTP_METHOD_POST:
                builder.url(url + params[0]);
                break;

            case HTTP_METHOD_GET:
                builder.url(url);
                builder.post((RequestBody) params[1]);
                break;

            case HTTP_METHOD_PUT:
                builder.url(url);
                Log.e(TAG, "put no implement");
                break;

            case HTTP_METHOD_DELETE:
                builder.url(url);
                Log.e(TAG, "delete no implement");
                break;
        }
    }

    private Object[] getParams(Map<String, Object> params) {
        Object[] result = new Object[3];

        StringBuilder stringBuilder = new StringBuilder("?");
        FormBody.Builder builder = new FormBody.Builder();
        Set<Map.Entry<String, Object>> paramsSet = params.entrySet();
        for (Map.Entry<String, Object> entry : paramsSet) {
            if (entry.getValue() instanceof String) {
                String key = entry.getKey();
                String value = (String) entry.getValue();

                builder.add(key, value);

                stringBuilder.append(key);
                stringBuilder.append("=");
                stringBuilder.append(value);
                stringBuilder.append("&");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        result[0] = stringBuilder.toString();
        result[1] = builder.build();
        result[2] = stringBuilder.toString();

        return result;
    }

    private void printRequestStatusLog(String url, int code, String message, boolean isSuccess) {
        if (isSuccess) {
            if (!MAPI.isDebug()) {
                return;
            }
            Log.i(TAG, "mHttp request success:");
            Log.i(TAG, "url : " + url);
            Log.i(TAG, "httpCode:" + code);
            Log.i(TAG, "message:" + message);

        } else {
            Log.e(TAG, "mHttp request failure:");
            Log.e(TAG, "url : " + url);
            Log.e(TAG, "httpCode:" + code);
            Log.e(TAG, "message:" + message);
        }
    }

    private <T> Observable<T> request(Map<String, Object> params, final Request request) {

        Object[] paramArray = getParams(params);
        final String url = request.url() + ((String) paramArray[2]);

        final Call call = getHttpInstance().newCall(request);
//        Response response = call.execute(); // 同步

        // 异步
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                printRequestStatusLog(url, -1, e.toString(), false);
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                response(url, response, mHttpResponseAble);
//            }
//        });

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                Response response = call.execute(); // 同步

                if (!MAPI.isDebug()) {
                    Log.i(TAG, "Server: " + response.header("Server"));
                    Log.i(TAG, "Date: " + response.header("Date"));
                    Log.i(TAG, "Vary: " + response.headers("Vary"));
                }

                String result = "";
                boolean isSuccess = true;
                try {
                    result = response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                    isSuccess = false;

                    MHttpException mHttpException = new MHttpException();
                    mHttpException.setCode(MHttpCode.M_HTTP_CODE_RESPONSE_DATA_EXCEPTION);
                    mHttpException.setMsg("数据异常");
                    mHttpException.setDescription(e.toString());
                    emitter.onError(mHttpException);
                }

                if (!response.isSuccessful()) {
                    isSuccess = false;
                    MHttpException mHttpException = new MHttpException();
                    mHttpException.setCode(response.code());
                    mHttpException.setMsg("网络不给力");
                    mHttpException.setDescription(result);
                    emitter.onError(mHttpException);

                } else {
                    isSuccess = true;
                    emitter.onNext(((T) result));

//                    Map<String, String> gist = getGsonInstance().fromJson(response.body().charStream(), Map.class);
//                    for (Map.Entry<String, String> entry : gist.entrySet()) {
//                        Log.i(TAG, entry.getKey() + ":" + String.valueOf(entry.getValue()));
//                    }
                }

                emitter.onComplete();

                printRequestStatusLog(url, response.code(), result, isSuccess);
            }
        }).subscribeOn(new IoScheduler());
    }

}
