package com.example.burt.lab.weekly_report;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.burt.lab.R;


public class WeeklyReportViewActivity extends Activity implements
        View.OnClickListener {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_report_view_activity);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:

                break;


        }
    }
}
