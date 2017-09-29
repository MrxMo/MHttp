package com.mrmo.mhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.mrmo.mhttp.net.AppAPI;
import com.mrmo.mhttp.net.HttpResultModel;
import com.mrmo.mhttp.net.TestModel;
import com.mrmo.mhttp.net.VerifyCodeModel;
import com.mrmo.mhttplib.MActivityProgressAble;
import com.mrmo.mhttplib.MHttpResponseImpl;

public class MainActivity extends AppCompatActivity implements MActivityProgressAble{

    private AppAPI appAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appAPI = new AppAPI(this);

//        appAPI.testAPI(new MHttpResponseImpl<TestModel>() {
//
//            @Override
//            public void onSuccessResult(int statusCode, final TestModel testModel) {
//                Toast.makeText(getApplicationContext(), ""+testModel.getImage(), Toast.LENGTH_SHORT).show();
//            }
//        });

        appAPI.test(new MHttpResponseImpl<VerifyCodeModel.DataBean>() {

            @Override
            public void onSuccessResult(int statusCode, final VerifyCodeModel.DataBean testModel) {
                Toast.makeText(getApplicationContext(), "jj"+testModel.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showProgress() {
        Toast.makeText(getApplicationContext(), "showProgress", Toast.LENGTH_SHORT).show();
        Log.i("", "showProgress");
    }

    @Override
    public void hideProgress() {
        Log.i("", "hideProgress");
    }

    /**
     * 添加请求记录数。
     */
    @Override
    public void addRequestRecordCount() {
        Log.i("", "addRequestRecordCount");
    }

    /**
     * 减少请求记录数。
     */
    @Override
    public void reduceRequestRecordCount() {
        Log.i("", "reduceRequestRecordCount");
    }

    /**
     * 是否请求完全部请求。
     */
    @Override
    public boolean isRequestAllFinish() {
        return false;
    }
}
