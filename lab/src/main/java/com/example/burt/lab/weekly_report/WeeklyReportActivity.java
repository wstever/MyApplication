package com.example.burt.lab.weekly_report;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.burt.lab.R;

import java.util.ArrayList;
import java.util.List;


public class WeeklyReportActivity extends Activity {
    private RecyclerView rv_finish, rv_next_week_plan, rv_senior_help, rv_bug_record;
    private WeeklyReportAdapter mPlanAdapter, mFinishedAdapter, mSeniorHelpAdapter, mBugRecordAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<WeeklyReportBean> mFinishedData = new ArrayList<>();
    private List<WeeklyReportBean> mPlanData = new ArrayList<>();
    private List<WeeklyReportBean> mSeniorHelpData = new ArrayList<>();
    private List<WeeklyReportBean> mBugRecordData = new ArrayList<>();
    public static final int REQUSET = 1;
    //判断是哪个RecyclerView
    int which = 0;

    private EditText et_week_time;
    private CardView ll_week_time;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_report_activity);

        et_week_time = (EditText) findViewById(R.id.et_week_time);

        rv_finish = (RecyclerView) findViewById(R.id.rv_finish);
        mLayoutManager = new LinearLayoutManager(this);
        rv_finish.setLayoutManager(mLayoutManager);
        rv_finish.setHasFixedSize(true);
        // 设置显示动画
        rv_finish.setItemAnimator(new DefaultItemAnimator());
        //初始化完成的内容，模板的内容
        WeeklyReportBean mWeeklyReportBean1 = new WeeklyReportBean(1, "[智慧餐厅]完成需求分析", false);
        WeeklyReportBean mWeeklyReportBean2 = new WeeklyReportBean(2, "[智慧衣柜]完成原型设计", false);
        mFinishedData.add(mWeeklyReportBean1);
        mFinishedData.add(mWeeklyReportBean2);
        mFinishedAdapter = new WeeklyReportAdapter(mFinishedData, 1);
        rv_finish.setAdapter(mFinishedAdapter);
        mFinishedAdapter.setOnItemClickListener(
                new WeeklyReportAdapter.OnItemClickListener() {
                    public void onItemClick(final View view, int position) {
                        // 设置点击动画
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
                        Toast.makeText(getApplicationContext(), "123qwe", Toast.LENGTH_LONG).show();

                        //跳转到编辑页面

                        Intent intent = new Intent();
                        intent.setClass(WeeklyReportActivity.this, WeeklyReportEditActivity.class);
                        intent.putExtra("which", 1);
                        //发送意图标示为REQUSET=1
                        startActivityForResult(intent, REQUSET);
                    }
                });
        mFinishedAdapter.setOnLongItemClickListener(
                            new WeeklyReportAdapter.OnLongItemClickListener() {
                        public void onLongItemClick(final View view, int position) {
                            //信不信我把你删除了
                            Toast.makeText(getApplicationContext(), "信不信我把你删除了", Toast.LENGTH_LONG).show();


                        }
                });


        rv_next_week_plan = (RecyclerView) findViewById(R.id.rv_next_week_plan);
        mLayoutManager = new LinearLayoutManager(this);
        rv_next_week_plan.setLayoutManager(mLayoutManager);
        rv_next_week_plan.setHasFixedSize(true);
        // 设置显示动画
        rv_next_week_plan.setItemAnimator(new DefaultItemAnimator());
        //初始化完成的内容，模板的内容
        WeeklyReportBean mWeeklyReportBean3 = new WeeklyReportBean(1, "[智慧餐厅]完成需求分析", false);
        WeeklyReportBean mWeeklyReportBean4 = new WeeklyReportBean(2, "[智慧餐厅]完成需求分析", false);
        mPlanData.add(mWeeklyReportBean3);
        mPlanData.add(mWeeklyReportBean4);
        mPlanAdapter = new WeeklyReportAdapter(mPlanData, 3);
        rv_next_week_plan.setAdapter(mPlanAdapter);
        mPlanAdapter.setOnItemClickListener(
                new WeeklyReportAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, int position) {
                        // 设置点击动画
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
                        //跳转到编辑页面

                        Intent intent = new Intent();
                        intent.setClass(WeeklyReportActivity.this, WeeklyReportEditActivity.class);
                        intent.putExtra("which", 2);

                        //发送意图标示为REQUSET=1
                        startActivityForResult(intent, REQUSET);
                    }
                });


        rv_senior_help = (RecyclerView) findViewById(R.id.rv_senior_help);
        mLayoutManager = new LinearLayoutManager(this);
        rv_senior_help.setLayoutManager(mLayoutManager);
        rv_senior_help.setHasFixedSize(true);
        // 设置显示动画
        rv_senior_help.setItemAnimator(new DefaultItemAnimator());
        //初始化完成的内容，模板的内容
        WeeklyReportBean mWeeklyReportBean5 = new WeeklyReportBean(1, "[智慧餐厅]完成需求分析", false);
        WeeklyReportBean mWeeklyReportBean6 = new WeeklyReportBean(2, "[智慧餐厅]完成需求分析", false);
        mSeniorHelpData.add(mWeeklyReportBean5);
        mSeniorHelpData.add(mWeeklyReportBean6);
        mSeniorHelpAdapter = new WeeklyReportAdapter(mSeniorHelpData, 3);

        rv_senior_help.setAdapter(mSeniorHelpAdapter);
        mSeniorHelpAdapter.setOnItemClickListener(
                new WeeklyReportAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, int position) {
                        // 设置点击动画
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
                        //跳转到编辑页面

                        Intent intent = new Intent();
                        intent.setClass(WeeklyReportActivity.this, WeeklyReportEditActivity.class);
                        intent.putExtra("which", 3);
                        //发送意图标示为REQUSET=1
                        startActivityForResult(intent, REQUSET);
                    }
                });


        rv_bug_record = (RecyclerView) findViewById(R.id.rv_bug_record);
        mLayoutManager = new LinearLayoutManager(this);
        rv_bug_record.setLayoutManager(mLayoutManager);
        rv_bug_record.setHasFixedSize(true);
        // 设置显示动画
        rv_bug_record.setItemAnimator(new DefaultItemAnimator());
        //初始化完成的内容，模板的内容
        WeeklyReportBean mWeeklyReportBean7 = new WeeklyReportBean(1, "[智慧餐厅]完成需求分析", false);
        WeeklyReportBean mWeeklyReportBean8 = new WeeklyReportBean(2, "[智慧餐厅]完成需求分析", false);
        mBugRecordData.add(mWeeklyReportBean7);
        mBugRecordData.add(mWeeklyReportBean8);
        mBugRecordAdapter = new WeeklyReportAdapter(mBugRecordData, 2);

        rv_bug_record.setAdapter(mBugRecordAdapter);
        mBugRecordAdapter.setOnItemClickListener(
                new WeeklyReportAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, int position) {
                        // 设置点击动画
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
                        //跳转到编辑页面
                        Intent intent = new Intent();
                        intent.setClass(WeeklyReportActivity.this, WeeklyReportEditActivity.class);
                        intent.putExtra("which", 4);
                        //发送意图标示为REQUSET=1
                        startActivityForResult(intent, REQUSET);
                    }
                });
    }

    //新增完成的内容
    public void add_a_finish_item(View view) {
        //跳转到编辑页面
        Intent intent = new Intent();
        intent.setClass(WeeklyReportActivity.this, WeeklyReportEditActivity.class);
        intent.putExtra("which", 1);

        //发送意图标示为REQUSET=1
        startActivityForResult(intent, REQUSET);
    }

    //新增下周计划
    public void add_a_plan_item(View view) {
        //跳转到编辑页面
        Intent intent = new Intent();
        intent.setClass(WeeklyReportActivity.this, WeeklyReportEditActivity.class);
        intent.putExtra("which", 2);
        //发送意图标示为REQUSET=1
        startActivityForResult(intent, REQUSET);
    }

    public void ask_for_advice(View view) {
        Toast.makeText(getApplicationContext(), "请教", Toast.LENGTH_LONG).show();
    }

    //新增本周重要bug情况
    public void add_a_bug_record(View view) {
        //跳转到编辑页面
        Intent intent = new Intent();
        intent.setClass(WeeklyReportActivity.this, WeeklyReportEditActivity.class);
        intent.putExtra("which", 4);
        //发送意图标示为REQUSET=1
        startActivityForResult(intent, REQUSET);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode标示请求的标示   resultCode表示有数据
        if (requestCode == WeeklyReportActivity.REQUSET && resultCode == RESULT_OK) {
            int status = data.getIntExtra("et_status", 0);
            String content = data.getStringExtra("et_content");
            boolean mflag = data.getBooleanExtra("et_flag", false);
            int which = data.getIntExtra("which", 0);
            WeeklyReportBean mWeeklyReportBean = new WeeklyReportBean(status, content, mflag);
            if (which == 1) {
                mFinishedData.add(mWeeklyReportBean);
                int position = mFinishedData.size();
                if (position > 0) {
                    mFinishedAdapter.notifyDataSetChanged();
                }
            } else if (which == 2) {
                mPlanData.add(mWeeklyReportBean);
                int position = mPlanData.size();
                if (position > 0) {
                    mPlanAdapter.notifyDataSetChanged();
                }
            } else if (which == 3) {
                mSeniorHelpData.add(mWeeklyReportBean);
                int position = mSeniorHelpData.size();
                if (position > 0) {
                    mSeniorHelpAdapter.notifyDataSetChanged();
                }
            } else if (which == 4) {
                mBugRecordData.add(mWeeklyReportBean);
                int position = mBugRecordData.size();
                if (position > 0) {
                    mBugRecordAdapter.notifyDataSetChanged();
                }
            }

        }
    }

}
