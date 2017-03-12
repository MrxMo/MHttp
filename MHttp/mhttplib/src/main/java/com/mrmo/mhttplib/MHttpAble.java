package com.mrmo.mhttplib;

import java.util.Map;

import io.reactivex.Observable;

/**
 * MHttpAsync 功能接口
 * Created by moguangjian on 15/10/10 13:54.
 */
public interface MHttpAble {

    String IS_SHOW_PROGRESS = "is_show_progress";

    int HTTP_METHOD_POST = 0;
    int HTTP_METHOD_GET = 1;
    int HTTP_METHOD_PUT = 2;
    int HTTP_METHOD_DELETE = 3;

    /**
     * 获取请求库实例
     *
     * @return
     */
    Object getInstance();

    /**
     * 销毁实例对象
     */
    void destroy();

    /**
     * 设置超时时间
     *
     * @param second
     */
    void setTimeout(int second);

    /**
     * 添加头部
     *
     * @param header 头部名字
     * @param value  头部值
     */
    void addHeader(String header, String value);

    /**
     * 删除指定头部
     *
     * @param header 头部名字
     */
    void removeHeader(String header);

    /**
     * 删除所有头部
     */
    void removeAllHeaders();

    /**
     * get请求
     *
     * @param url    请求地址
     * @param params 请求参数
     */
    <T> Observable<T> get(String url, Map<String, Object> params);

    <T> Observable<T> post(String url, Map<String, Object> params);

    <T> Observable<T> put(String url, Map<String, Object> params);

    <T> Observable<T> delete(String url, Map<String, Object> params);


}
