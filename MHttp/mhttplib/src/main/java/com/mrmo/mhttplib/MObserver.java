package com.mrmo.mhttplib;

import rx.Observer;

/**
 * Created by moguangjian on 2017/9/28.
 */
public interface MObserver<T> extends Observer<T> {

    void onPrepare();
}
