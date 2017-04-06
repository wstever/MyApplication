package com.example.burt.lab.project_report;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.burt.lab.R;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProjectReportActivity extends Activity {

    private RecyclerView rv_project_finish, rv_project_next_week_plan, rv_project_senior_help;
    private ProjectReportAdapter mProjectFinishedAdapter, mProjectPlanAdapter, mProjectSeniorHelpAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<ProjectReportBean> mProjectFinishedData = new ArrayList<>();
    private List<ProjectReportBean> mProjectPlanData = new ArrayList<>();
    private List<ProjectReportBean> mProjectSeniorHelpData = new ArrayList<>();
    public static final int REQUSET = 2;
    private ImageButton ib_project_progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_report_activity);
        ib_project_progress = (ImageButton) this.findViewById(R.id.ib_project_progress);
        ib_project_progress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                set_project_progress_imagebutton();
            }
        });

/*
* 本周完成的内容
*/
        rv_project_finish = (RecyclerView) findViewById(R.id.rv_project_finish);
        mLayoutManager = new LinearLayoutManager(this);
        rv_project_finish.setLayoutManager(mLayoutManager);
        rv_project_finish.setHasFixedSize(true);
        // 设置显示动画
        rv_project_finish.setItemAnimator(new DefaultItemAnimator());
        //初始化完成的内容，模板的内容
        ProjectReportBean mProjectReportBean1 = new ProjectReportBean(1, "[]", "[XXX]XXXXX", false);
        ProjectReportBean mProjectReportBean2 = new ProjectReportBean(2, "[]", "[XXX]XXXXX", false);
        mProjectFinishedData.add(mProjectReportBean1);
        mProjectFinishedData.add(mProjectReportBean2);
        mProjectFinishedAdapter = new ProjectReportAdapter(mProjectFinishedData, 1);
        rv_project_finish.setAdapter(mProjectFinishedAdapter);
        mProjectFinishedAdapter.setOnItemClickListener(
                new ProjectReportAdapter.OnItemClickListener() {
                    public void onItemClick(final View view, int position) {
                        // 设置点击动画
                        showAnimate(view);
                        //弹出你想要做的操作的对话框
                        int which = 1;
                        myOperationDialog(position, which);
                    }
                });
/*
* 下周的项目计划
*/
        rv_project_next_week_plan = (RecyclerView) findViewById(R.id.rv_project_next_week_plan);
        mLayoutManager = new LinearLayoutManager(this);
        rv_project_next_week_plan.setLayoutManager(mLayoutManager);
        rv_project_next_week_plan.setHasFixedSize(true);
        // 设置显示动画
        rv_project_next_week_plan.setItemAnimator(new DefaultItemAnimator());
        //初始化完成的内容，模板的内容
        ProjectReportBean mProjectReportBean3 = new ProjectReportBean(1, "[智慧餐厅]", "[XXX]XXXXX", false);
        ProjectReportBean mProjectReportBean4 = new ProjectReportBean(2, "[智慧餐厅]", "[XXX]XXXXX", false);
        mProjectPlanData.add(mProjectReportBean3);
        mProjectPlanData.add(mProjectReportBean4);
        mProjectPlanAdapter = new ProjectReportAdapter(mProjectPlanData, 2);
        rv_project_next_week_plan.setAdapter(mProjectPlanAdapter);
        mProjectPlanAdapter.setOnItemClickListener(
                new ProjectReportAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, int position) {
                        // 设置点击动画
                        showAnimate(view);
                        //弹出你想要做的操作的对话框
                        int which = 2;
                        myOperationDialog(position, which);
                    }
                });
/*
* 项目中需要师兄师姐支持的内容
*
*/
        rv_project_senior_help = (RecyclerView) findViewById(R.id.rv_project_senior_help);
        mLayoutManager = new LinearLayoutManager(this);
        rv_project_senior_help.setLayoutManager(mLayoutManager);
        rv_project_senior_help.setHasFixedSize(true);
        // 设置显示动画
        rv_project_senior_help.setItemAnimator(new DefaultItemAnimator());
        //初始化完成的内容，模板的内容
        ProjectReportBean mProjectReportBean5 = new ProjectReportBean(1, "智慧餐厅", "[XXX]XXXXX", false);
        ProjectReportBean mProjectReportBean6 = new ProjectReportBean(2, "智慧餐厅", "[XXX]XXXXX", false);
        mProjectSeniorHelpData.add(mProjectReportBean5);
        mProjectSeniorHelpData.add(mProjectReportBean6);
        mProjectSeniorHelpAdapter = new ProjectReportAdapter(mProjectSeniorHelpData, 3);

        rv_project_senior_help.setAdapter(mProjectSeniorHelpAdapter);
        mProjectSeniorHelpAdapter.setOnItemClickListener(
                new ProjectReportAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, final int position) {
                        // 设置点击动画
                        showAnimate(view);
                        //弹出你想要做的操作的对话框
                        int which = 3;
                        myOperationDialog(position, which);
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
    public void myOperationDialog(final int position, final int which) {
        // 创建数据
        final String[] array_operation = new String[]{"修改内容", "复制内容", "删除内容"};
        // 创建对话框构建器
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        // 设置参数
        mBuilder.setIcon(R.mipmap.ic_launcher).setTitle("提示")
                .setItems(array_operation, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which_operation) {
                        switch (which_operation) {
                            //跳转到编辑页面
                            case 0:
                                Intent intent = new Intent();
                                intent.setClass(ProjectReportActivity.this, ProjectReportEditActivity.class);
                                intent.putExtra("which", which);
                                //这里要注意了!
                                if (which == 1) {
                                    intent.putExtra("original_content", mProjectFinishedData.get(position).getContent());
                                } else if (which == 2) {
                                    intent.putExtra("original_content", mProjectPlanData.get(position).getContent());
                                } else if (which == 3) {
                                    intent.putExtra("original_content", mProjectSeniorHelpData.get(position).getContent());
                                }
                                //*如果传过去的是-1就新增,如果是>0的,就修改内容
                                intent.putExtra("get_position", position);
                                // 发送意图标示为REQUSET=2
                                startActivityForResult(intent, REQUSET);
                                break;
                            //复制相应的内容
                            case 1:

                                break;
                            //由他是哪个Rv传来的,判断应该删除哪个内容!
                            case 2:
                                doDelete(which);


                                break;
                        }
                    }
                });
        mBuilder.create().show();
    }

    private void doDelete(final int which) {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("你确认要删除么?")
                .setContentText("一经删除,无法修改!")
                .setConfirmText("是的,删除掉吧!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog
                                .setTitleText("删除!")
                                .setContentText("您选中的内容已被删除!")
                                .setConfirmText("好的")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        if (which == 1) {
                            int position = mProjectFinishedData.size();
                            if (position > 0) {
                                mProjectFinishedData.remove(position - 1);
                                mProjectFinishedAdapter.notifyDataSetChanged();
                            }
                        } else if (which == 2) {
                            int position = mProjectPlanData.size();
                            if (position > 0) {
                                mProjectPlanData.remove(position - 1);
                                mProjectPlanAdapter.notifyDataSetChanged();
                            }
                        } else if (which == 3) {
                            int position = mProjectSeniorHelpData.size();
                            if (position > 0) {
                                mProjectSeniorHelpData.remove(position - 1);
                                mProjectSeniorHelpAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                })
                .show();
    }

    public void set_project_progress_imagebutton() {
        // 创建数据
        final String[] array_progress = new String[]{"正常", "延误", "严重"};
        // 创建对话框构建器
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        // 设置参数
        mBuilder.setIcon(R.mipmap.ic_launcher).setTitle("项目进度情况")
                .setItems(array_progress, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which_operation) {
                        switch (which_operation) {
                            case 0:
                                ib_project_progress.setBackgroundColor(Color.GREEN);
                                break;
                            case 1:
                                ib_project_progress.setBackgroundColor(Color.YELLOW);
                                break;
                            case 2:
                                ib_project_progress.setBackgroundColor(Color.RED);
                                break;
                        }

                    }
                });
        mBuilder.create().show();
    }


    //新增本周项目完成的内容
    public void add_a_project_finish_item(View view) {
        //跳转到编辑页面
        Intent intent = new Intent();
        intent.setClass(ProjectReportActivity.this, ProjectReportEditActivity.class);
        intent.putExtra("which", 1);
        intent.putExtra("get_position", -1);
        //发送意图标示为REQUSET=1
        startActivityForResult(intent, REQUSET);
    }

    //新增下周项目计划
    public void add_a_project_plan_item(View view) {
        //跳转到编辑页面
        Intent intent = new Intent();
        intent.setClass(ProjectReportActivity.this, ProjectReportEditActivity.class);
        intent.putExtra("which", 2);
        intent.putExtra("get_position", -1);
        //发送意图标示为REQUSET=1
        startActivityForResult(intent, REQUSET);
    }

    public void project_ask_for_advice(View view) {
        Toast.makeText(getApplicationContext(), "请教", Toast.LENGTH_LONG).show();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode标示请求的标示   resultCode表示有数据
        if (requestCode == ProjectReportActivity.REQUSET && resultCode == RESULT_OK) {
            int status = data.getIntExtra("et_status", 0);
            String content = data.getStringExtra("et_content");
            boolean mflag = data.getBooleanExtra("et_flag", false);
            int which = data.getIntExtra("which", 0);
            int get_position = data.getIntExtra("get_position", 0);
            String project = "智慧餐厅";
            ProjectReportBean mProjectReportBean = new ProjectReportBean(status, project, content, mflag);
            if (which == 1) {
                if (get_position >= 0) {
                    mProjectFinishedData.get(get_position).setContent(content);
                    mProjectFinishedAdapter.notifyDataSetChanged();
                } else {
                    mProjectFinishedData.add(mProjectReportBean);
                    int position = mProjectFinishedData.size();
                    if (position > 0) {
                        mProjectFinishedAdapter.notifyDataSetChanged();
                    }
                }
            } else if (which == 2) {
                if (get_position >= 0) {
                    mProjectPlanData.get(get_position).setContent(content);
                    mProjectPlanAdapter.notifyDataSetChanged();
                } else {
                    mProjectPlanData.add(mProjectReportBean);
                    int position = mProjectPlanData.size();
                    if (position > 0) {
                        mProjectPlanAdapter.notifyDataSetChanged();
                    }
                }
            } else if (which == 3) {
                if (get_position >= 0) {
                    mProjectSeniorHelpData.get(get_position).setContent(content);
                    mProjectSeniorHelpAdapter.notifyDataSetChanged();
                } else {
                    mProjectSeniorHelpData.add(mProjectReportBean);
                    int position = mProjectSeniorHelpData.size();
                    if (position > 0) {
                        mProjectSeniorHelpAdapter.notifyDataSetChanged();
                    }
                }
            }

        }
    }

}
