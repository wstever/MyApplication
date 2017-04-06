package com.example.burt.lab.weekly_report;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.burt.lab.R;

import java.util.ArrayList;
import java.util.List;


public class WeekReportFragment extends Fragment {
    private View rootView;

    private RecyclerView recyclerView;

    public static final int REQUSET = 2;
    private WeeklyReportRvAdapter mWeeklyReportRvAdapter;
    private List<WeeklyReportCustomRvBean> mWeeklyReportList;
    private FloatingActionButton fab;

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
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mWeeklyReportList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mWeeklyReportList.add(new WeeklyReportCustomRvBean("12 ", "12 ", "12 " + i, "12 " + i));
        }

        mWeeklyReportRvAdapter = new WeeklyReportRvAdapter(mWeeklyReportList);
        recyclerView.setAdapter(mWeeklyReportRvAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//垂直线性布局

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), WeeklyReportActivity.class);
                startActivity(intent);
            }
        });

        mWeeklyReportRvAdapter.setOnItemClickListener(new WeeklyReportRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),
                        WeeklyReportViewActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // 设置点击动画
                showAnimate(view);
                //弹出你想要做的操作的对话框
                myOperationDialog(position);
            }
        });
    }

    //设置点击动画
    public void showAnimate(final View view) {
        view.animate()
                .translationZ(15F).setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.animate()
                                .translationZ(1f)
                                .setDuration(500).start();
                    }
                }).start();
    }

    //弹出你想要做的操作的对话框
    public void myOperationDialog(final int position) {
        // 创建数据
        final String[] array_operation = new String[]{"查看内容", "修改内容", "删除内容"};
        // 创建对话框构建器
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        // 设置参数
        mBuilder.setIcon(R.mipmap.b0).setTitle("友情提醒")
                .setItems(array_operation, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which_operation) {
                        switch (which_operation) {
                            //跳转到编辑页面
                            case 0:
                                Intent intent = new Intent();
                                intent.setClass(getActivity(), WeeklyReportEditActivity.class);
                                //*如果传过去的是-1就新增,如果是>0的,就修改内容
                                intent.putExtra("get_position", position);
                                // 发送意图标示为REQUSET=2
                                startActivityForResult(intent, REQUSET);
                                break;
                            //复制相应的内容
                            case 1:
                                Intent intent_edit = new Intent();
                                intent_edit.setClass(getActivity(), WeeklyReportEditActivity.class);
                                //*如果传过去的是-1就新增,如果是>0的,就修改内容
                                intent_edit.putExtra("get_position", position);
                                // 发送意图标示为REQUSET=2
                                startActivityForResult(intent_edit, REQUSET);
                                break;
                            //判断应该删除哪个内容!
                            case 2:
                                int position = mWeeklyReportList.size();
                                if (position > 0) {
                                    mWeeklyReportList.remove(position - 1);
                                    mWeeklyReportRvAdapter.notifyDataSetChanged();
                                }

                                break;
                        }
                    }
                });
        mBuilder.create().show();
    }
}