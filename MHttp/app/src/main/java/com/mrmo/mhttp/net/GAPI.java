package com.mrmo.mhttp.net;

import android.content.Context;
import android.os.Build;

import com.mrmo.mhttplib.MAPI;
import com.mrmo.mhttplib.MHttpAble;
import com.mrmo.mhttplib.MHttpAsync;
import com.mrmo.mhttplib.MOkHttp;
import com.mrmo.mhttplib.utils.MMd5Util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.host;

/**
 * Created by moguangjian on 15/10/16 11:23.
 */
public class GAPI extends MAPI {

    private static final String API_VERSION = "v1";
    private String host = "http://bapp.xuebaokk.com/"+API_VERSION+"/";

    public GAPI(Context context) {
        super(context);
    }

    @Override
    protected String getApiServerPrefixDebug() {
        return host;
    }

    @Override
    protected String getApiServerPrefix() {
        return host;
    }

    @Override
    protected MHttpAble getMHttpAble() {
        return new MHttpAsync(context);
    }

    @Override
    protected boolean getDebugStatus() {
        return true;
    }

    @Override
    protected boolean requestHttpPrepare(int method, String url, Map<String, Object> params) {

        return true;
}

    protected static String getSignature(Map<String, Object> params) {
        String appKey = "test";
        Set<Map.Entry<String, Object>> set = params.entrySet();
        Iterator<Map.Entry<String, Object>> iterator= set.iterator();

        String[] keys = new String[params.size()];
        int i = 0;
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            keys[i] = entry.getKey();
            i++;
        }

        Arrays.sort(keys);

        StringBuilder stringBuilder = new StringBuilder();
        for (String values : keys) {
            stringBuilder.append(values);
            stringBuilder.append("=");
            stringBuilder.append(params.get(values));
            stringBuilder.append("&");
        }

        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

        stringBuilder.append(appKey);

        String token = MMd5Util.getMD5_32(stringBuilder.toString());

        return token;
    }

}
