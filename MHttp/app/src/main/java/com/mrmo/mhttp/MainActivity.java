package com.mrmo.mhttp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mrmo.mhttp.net.AppAPI;
import com.mrmo.mhttplib.MHttpResponseAble;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AppAPI(this).testAPI(new MHttpResponseAble() {
            @Override
            public void onPrepare() {

            }

            @Override
            public void onFailure(int statusCode, Object object) {

            }

            @Override
            public void onFailure(Context context, int statusCode, Object object) {

            }

            @Override
            public void onSuccess(int statusCode, Object object) {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }
        });
    }
}
