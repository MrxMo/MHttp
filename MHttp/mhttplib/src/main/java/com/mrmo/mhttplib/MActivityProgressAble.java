package com.mrmo.mhttplib;

/**
 * Created by moguangjian on 2017/3/18.
 */

public interface MActivityProgressAble {

    void showProgress();

    void hideProgress();

    /**
     * 添加请求记录数。
     */
    void addRequestRecordCount();

    /**
     * 减少请求记录数。
     */
    void reduceRequestRecordCount();

    /**
     * 是否请求完全部请求。
     * @return
     */
    boolean isRequestAllFinish();
}
