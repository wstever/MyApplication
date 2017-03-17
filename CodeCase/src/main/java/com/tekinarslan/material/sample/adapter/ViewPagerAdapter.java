package com.tekinarslan.material.sample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tekinarslan.material.sample.fragment.CodeCaseFragment;
import com.tekinarslan.material.sample.fragment.DoubanFragment;
import com.tekinarslan.material.sample.fragment.SampleFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String[] titles;

    public ViewPagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        titles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            // Open FragmentTab1.java
            case 0:
                return CodeCaseFragment.newInstance(position);
            case 1:
                return SampleFragment.newInstance(position);
            case 2:
                return DoubanFragment.newInstance(position);
        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}