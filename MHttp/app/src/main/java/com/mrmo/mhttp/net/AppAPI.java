package com.mrmo.mhttp.net;

import android.content.Context;

import com.mrmo.mhttplib.MHttpResponseAble;
import com.mrmo.mhttplib.MHttpSubscriber;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

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

        post("getWelcome.html", map)
                .map(new GHttpResultConverter<TestModel>(gson))
                .map(new Function<TestModel, TestModel>() {

                    @Override
                    public TestModel apply(@NonNull TestModel testModel) throws Exception {
                        return null;
                    }
                });


        requestSubscribe(
                post("getWelcome.html", map),
                new GHttpResultConverter<TestModel>(gson),
                new MHttpSubscriber<TestModel>(context, mHttpResponseAble));

    }

    /**
     * 测试api
     *
     * @param mHttpResponseAble
     */
    public void test(MHttpResponseAble mHttpResponseAble) {
        Map map = new HashMap();
        map.put("phone", "15521212697");
        map.put("type", "1");

        post("User/getcode", map)
                .map(new GHttpResultConverter<VerifyCodeModel>(gson))
                .map(new Function<VerifyCodeModel, VerifyCodeModel.DataBean>() {

                    @Override
                    public VerifyCodeModel.DataBean apply(@NonNull VerifyCodeModel testModel) throws Exception {
                        return testModel.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new MHttpSubscriber<VerifyCodeModel.DataBean>(context, mHttpResponseAble));

//        requestSubscribe(
//                post("User/getcode", map),
//                new GHttpResultConverter<HttpResultModel>(gson),
//                new MHttpSubscriber<HttpResultModel>(context, mHttpResponseAble));
    }
}
