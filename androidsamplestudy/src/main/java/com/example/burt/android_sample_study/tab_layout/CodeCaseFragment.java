package com.example.burt.android_sample_study.tab_layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.burt.android_sample_study.R;


public class CodeCaseFragment extends Fragment {


    private View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.tablayout_fragment_page, container, false);
            initView(rootView);// 控件初始化
        }
        return rootView;
    }

    private void initView(View rootView) {
        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText("CodeCaseFragment");
    }


}