package com.example.burt.android_sample_study.tab_layout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.example.burt.android_sample_study.R;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout_new_main);

        //Fragment+ViewPager+FragmentViewPager组合的使用
        // 绑定viewpager与tablayout
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new PageFragment());
        fragments.add(new CodeCaseFragment());
        // 新建适配器
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        // 设置适配器
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        // 直接绑定viewpager，消除了以前的需要设置监听器的繁杂工作
        tabLayout.setupWithViewPager(viewPager);

        //1.MODE_SCROLLABLE模式
        // tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //2.MODE_FIXED模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
