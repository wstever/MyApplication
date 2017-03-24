package com.example.burt.android_sample_study.horizontal_scrollview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.burt.android_sample_study.R;

public class HorizontalScrollViewActivity extends Activity {
    //private HorizontalScrollView scrollView;
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizon_scrollview_main);
        //scrollView = (HorizontalScrollView) this.findViewById(R.id.scroll_view);
        linear = (LinearLayout) this.findViewById(R.id.linear);
        createChildLinearLayout();
    }

    private void createChildLinearLayout() {
        for (int i = 0; i < 50; i++) {
            LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout myLinear = new LinearLayout(this);
            ll_lp.setMargins(20, 0, 20, 20);
            myLinear.setOrientation(LinearLayout.VERTICAL);
            myLinear.setTag(i);
            linear.addView(myLinear, ll_lp);

            LinearLayout.LayoutParams tv_lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            final TextView textView = new TextView(this);
            textView.setText(i + "");
            textView.setGravity(Gravity.CENTER);
            myLinear.addView(textView, tv_lp);

            myLinear.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(HorizontalScrollViewActivity.this, v.getTag().toString(),
                            Toast.LENGTH_SHORT).show();
                    textView.setTextColor(Color.RED);
                }
            });
        }
    }
}