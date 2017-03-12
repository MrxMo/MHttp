package com.mrmo.mhttp.net;

import android.content.Context;

import com.mrmo.mhttplib.MAPI;
import com.mrmo.mhttplib.MHttpAble;
import com.mrmo.mhttplib.MOkHttp;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by moguangjian on 15/10/16 11:23.
 */
public class GAPI extends MAPI {

    public GAPI(Context context) {
        super(context);
    }

    @Override
    protected String getApiServerPrefixDebug() {
        return "http://qpzy.callmlt.com/api/";
    }

    @Override
    protected String getApiServerPrefix() {
        return "http://qpzy.callmlt.com/api/";
    }

    @Override
    protected MHttpAble getMHttpAble() {
        return new MOkHttp(context);
    }

    @Override
    protected boolean getDebugStatus() {
        return true;
    }

    @Override
    protected boolean requestHttpPrepare(int method, String url, Map<String, Object> params) {

        return true;
    }

}
