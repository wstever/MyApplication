package com.tekinarslan.material.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.tekinarslan.material.sample.data.retrofit.RetrofitRepository;


/**
 * fragment 基类
 * <p/>
 * Created on 16/6/27.
 */
public class BaseFragment extends Fragment {
    RetrofitRepository mRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = RetrofitRepository.getInstance(getActivity());
    }
}
