package com.mrmo.mhttplib;

import java.util.Map;

import rx.Observable;

/**
 * http请求桥
 * Created by moguangjian on 15/10/10 15:48.
 */
public class MHttpBridge implements MHttpAble {

    private MHttpAble mHttpAble;

    public MHttpBridge() {

    }

    public MHttpBridge(MHttpAble mHttpAble) {
        this.mHttpAble = mHttpAble;
    }

    public MHttpAble getMHttpAble() {
        return mHttpAble;
    }

    public void setMHttpAble(MHttpAble mHttpAble) {
        this.mHttpAble = mHttpAble;
    }

    @Override
    public Object getInstance() {
        return mHttpAble.getInstance();
    }

    @Override
    public void destroy() {
        mHttpAble.destroy();
    }

    @Override
    public void setTimeout(int second) {
        mHttpAble.setTimeout(second);
    }

    @Override
    public void addHeader(String header, String value) {
        mHttpAble.addHeader(header, value);
    }

    @Override
    public void removeHeader(String header) {
        mHttpAble.removeHeader(header);
    }

    @Override
    public void removeAllHeaders() {
        mHttpAble.removeAllHeaders();
    }

    @Override
    public <T> Observable<T> get(String url, Map<String, Object> params) {
        return mHttpAble.get(url, params);
    }

    @Override
    public <T> Observable<T> post(String url, Map<String, Object> params) {
        return mHttpAble.post(url, params);
    }

    @Override
    public <T> Observable<T> put(String url, Map<String, Object> params) {
        return mHttpAble.put(url, params);
    }

    @Override
    public <T> Observable<T> delete(String url, Map<String, Object> params) {
        return mHttpAble.delete(url, params);
    }
}
