package com.tekinarslan.material.sample.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.adapter.Top250Adapter;
import com.tekinarslan.material.sample.bean.Top250Bean;
import com.tekinarslan.material.sample.util.RecyclerViewUtil.GridMarginDecoration;
import com.udaye.library.pullloadlibrary.LinearRecyclerView;
import com.udaye.library.pullloadlibrary.OnLoadMoreListener;
import com.udaye.library.pullloadlibrary.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DoubanFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_POSITION = "position";

    private int position;


    LinearRecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Top250Adapter top250Adapter;

    List<Top250Bean.SubjectsBean> mList;
    Top250Bean mTop250Bean;


    public static DoubanFragment newInstance(int position) {
        DoubanFragment fragment = new DoubanFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt(ARG_POSITION, position);
        fragment.setArguments(mBundle);
        return fragment;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_top250, container, false);

        recyclerView = (LinearRecyclerView) rootView.findViewById(R.id.rv_recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new GridMarginDecoration(
                getResources().getDimensionPixelSize(R.dimen.grid_item_spacing)));
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(SuperRecyclerView recyclerView) {
                if (mTop250Bean != null && mTop250Bean.isHasNext()) {
                    recyclerView.showFootProgress();
                    requestData(mTop250Bean.getNextIndex(), 20);
                } else {
                    recyclerView.showFootProgressEnd();
                }
            }
        });
        setPreLoadData();
    }


    @Override
    public void onRefresh() {
        if (top250Adapter != null) {
            top250Adapter.clear();
        }
        requestData(0, 20);
    }

    public void setPreLoadData() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }


    private void requestData(int start, int count) {
        mRepository.getTop250Movie(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Top250Bean>() {
                    @Override
                    public void onCompleted() {
                        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onNext(Top250Bean top250Bean) {
                        if (top250Bean != null) {
                            recyclerView.showFootProgressEnd();

                            mTop250Bean = top250Bean;
                            mList = top250Bean.getSubjects();
                            if (top250Adapter == null) {
                                top250Adapter = new Top250Adapter(getContext(), mList);
                                recyclerView.setAdapter(top250Adapter);
                            } else {
                                top250Adapter.update((ArrayList<Top250Bean.SubjectsBean>) mList);
                            }
                        }
                    }
                });
    }

}