package com.mrmo.mhttp.net;

import android.content.Context;

import com.mrmo.mhttplib.MHttpResponseAble;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by moguangjian on 15/10/16 11:32.
 */
public class AppAPI extends GAPI {

    private final String API_GET_ABOUT_ME = "getWelcome.html";

    public AppAPI(Context context) {
        super(context);
    }

    /**
     * @param mHttpResponseAble
     */
    public void testAPI(MHttpResponseAble mHttpResponseAble) {

        Map map = new HashMap();
        map.put("name", "a");
        map.put("sex", "boy");

        get(API_GET_ABOUT_ME, map, mHttpResponseAble);
    }

}
