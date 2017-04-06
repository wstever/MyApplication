package com.example.burt.android_sample_study.spider;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.burt.android_sample_study.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SpiderTextViewActivity extends Activity {
    private static final int UPDATEMSG = 0;
    TextView tv;

    Runnable runnable = new Runnable() {
        public void run() {
            getData();
            // 执行完毕后给handler发送一个空消息
            //  handler.sendEmptyMessage(0);
        }
    };

    String a;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //处理UI
            // 当收到消息时就会执行这个方法
            if (msg.what == UPDATEMSG)
                a = msg.obj.toString();
            tv.setMovementMethod(LinkMovementMethod.getInstance());//加这句才能让里面的超链接生效
            tv.setText(Html.fromHtml(a));
        }
    };


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spider_tv_layout);
        tv = (TextView) this.findViewById(R.id.tv);
        // 开辟一个线程
        new Thread(runnable).start();


    }

    private void getData() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.iteveryday.cn/category-tech/1980.html").get();
            String a = null;
            Elements listDiv = doc.getElementsByAttributeValue("bosszone", "content");
            for (Element element : listDiv) {
                a = element.html();
                System.out.println(a);
            }

            // 循环发送消息，实现内部通信
            Message msg = new Message();
            msg.what = UPDATEMSG;
            msg.obj = a;
            handler.sendMessage(msg);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
