package com.mrmo.mhttplib;


import android.content.Context;
import android.support.annotation.Nullable;

/**
 * http请求回调接口
 *
 * @author moguangjian
 * @date 15/10/10
 */
public interface MHttpResponseAble<T> {

    /**
     * 请求准备(还没有发起请求)
     */
    void onPrepare();

    /**
     * 请求失败
     *
     * @param context
     * @param statusCode
     * @param object
     */
    void onFailure(@Nullable Context context, int statusCode, Object object);

    /**
     * 请求成功
     *
     * @param statusCode
     * @param t
     */
    void onSuccess(int statusCode, T t);

    /**
     * 请求结束
     */
    void onFinish();

    /**
     * 请求进度
     *
     * @param bytesWritten
     * @param totalSize
     */
    void onProgress(long bytesWritten, long totalSize);
}
