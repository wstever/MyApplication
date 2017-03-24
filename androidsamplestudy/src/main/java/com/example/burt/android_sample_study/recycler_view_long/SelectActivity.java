package com.example.burt.android_sample_study.recycler_view_long;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.burt.android_sample_study.R;
import com.example.burt.android_sample_study.weekly_report.WeeklyReportActivity;

public class SelectActivity extends AppCompatActivity {

    private Button btn_list;
    private Button btn_recycler;
    private Button btn_bug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.long_rv_activity_long_click);

        btn_list = (Button) findViewById(R.id.btn_listview);
        btn_recycler = (Button) findViewById(R.id.btn_recycler);
        btn_bug = (Button) findViewById(R.id.btn_weekly_report);
        btn_list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SelectActivity.this, ListViewActivity.class));
            }
        });
        btn_recycler.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SelectActivity.this, RecyclerViewActivity.class));
            }
        });

        btn_bug.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SelectActivity.this, WeeklyReportActivity.class));
            }
        });
        Button fab = (Button) findViewById(R.id.button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
