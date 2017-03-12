package com.mrmo.mhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mrmo.mhttp.net.AppAPI;
import com.mrmo.mhttp.net.TestModel;
import com.mrmo.mhttplib.MHttpResponseImpl;

public class MainActivity extends AppCompatActivity {

    private AppAPI appAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appAPI = new AppAPI(this);

        appAPI.testAPI(new MHttpResponseImpl<TestModel>() {

            @Override
            public void onSuccessResult(int statusCode, final TestModel testModel) {
                Toast.makeText(getApplicationContext(), ""+testModel.getImage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
