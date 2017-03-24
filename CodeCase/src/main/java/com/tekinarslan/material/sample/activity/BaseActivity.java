package com.tekinarslan.material.sample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tekinarslan.material.sample.data.retrofit.RetrofitRepository;


public class BaseActivity extends AppCompatActivity {
    RetrofitRepository mRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = RetrofitRepository.getInstance(this);
    }

    /*abstract void initActionBar();
*/
}
