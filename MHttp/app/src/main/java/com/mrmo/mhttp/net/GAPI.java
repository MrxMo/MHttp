package com.mrmo.mhttp.net;

import android.content.Context;
import android.os.Build;

import com.mrmo.mhttplib.MAPI;
import com.mrmo.mhttplib.MHttpAble;
import com.mrmo.mhttplib.MHttpAsync;
import com.mrmo.mhttplib.utils.MMd5Util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


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

        String apiName = url.replaceAll(host, "").replaceAll("/", ".");
        params.put("i", apiName); // 调用者
        params.put("u", "app"); // 调用者
        params.put("t", API_VERSION); // 版本号
        params.put("x", "test"); // 终端
        params.put("b", "auto"); // 设备号
        params.put("a", "api"); // 系统
        params.put("pageSize", "15");
        params.put("os", "Android" + Build.VERSION.RELEASE);                        // 客户端系统信息	iPhone; iOS 9.3.1;
//        params.put("app_ver", MPackageUtil.getAppVersionName(context));             // 应用版本号
        params.put("d", System.currentTimeMillis()/1000); // 请求时间 1489422824
        params.put("sign", getSignature(params));
        params.put("c", "0"); // 是否开启验证。1:需要 / 0:不需要

        return true;
    }

    protected static String getSignature(Map<String, Object> params) {
        String appKey = "woshidd123!@#";
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
