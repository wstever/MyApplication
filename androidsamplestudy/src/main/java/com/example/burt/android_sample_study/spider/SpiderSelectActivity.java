package com.example.burt.android_sample_study.spider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.burt.android_sample_study.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SpiderSelectActivity extends Activity {


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spider_select_layout);


    }

    public void bt_wv(View v) {
        Intent intent = new Intent(SpiderSelectActivity.this, SpiderTextViewActivity.class);
        startActivity(intent);
    }

    public void bt_tv(View v) {
        Intent intent = new Intent(SpiderSelectActivity.this, SpiderWebViewActivity.class);
        startActivity(intent);
    }


}
