package com.mrmo.mhttp.net;

import android.content.Context;

import com.mrmo.mhttplib.MHttpResponseAble;
import com.mrmo.mhttplib.MHttpSubscriber;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by moguangjian on 15/10/16 11:32.
 */
public class AppAPI extends GAPI {

    public AppAPI(Context context) {
        super(context);
    }

    /**
     * 测试API
     *
     * @param mHttpResponseAble
     */
    public void testAPI(MHttpResponseAble mHttpResponseAble) {
        Map map = new HashMap();
        map.put("name", "a");
        map.put("sex", "boy");

        requestSubscribe(
                get("getWelcome.html", map),
                new GHttpResultConverter<TestModel>(gson),
                new MHttpSubscriber<TestModel>(context, mHttpResponseAble));

    }
}
