package com.example.burt.android_sample_study.weekly_report;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.burt.android_sample_study.R;


public class WeeklyReportEditActivity extends Activity implements
        View.OnClickListener {


    private RelativeLayout rl_weekly_report_battery, rl_weekly_report_light;
    private RelativeLayout pop_home_rl, pop_msg_rl, pop_share_rl, pop_share1_rl, pop_share2_rl;

    private TextView tv_weekly_report_edit;
    private ImageView iv_weekly_report_battery, iv_weekly_report_light;
    private Button bt_add;
    private EditText et_weekly_report_edit;
    private String str_content_weekly_report_edit;
    //下拉展开按钮
    private PopupWindow popupWindow;
    private View popView;
    //进度

    private int status;
    //下拉列表宽度
    private int width;

    private int which;
    private Boolean mFlag = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_report_edit_activity);
        Intent intent = getIntent();
        which = intent.getIntExtra("which", 0);
        initView();
    }

    public void initView() {
        //电池按钮
        rl_weekly_report_battery = (RelativeLayout) this.findViewById(R.id.rl_weekly_report_battery);
        rl_weekly_report_battery.setOnClickListener(this);
        et_weekly_report_edit = (EditText) this.findViewById(R.id.et_weekly_report_edit);
        tv_weekly_report_edit = (TextView) this.findViewById(R.id.tv_weekly_report_edit);
        //进度描述


        //bug解决了还是进行中
        rl_weekly_report_light = (RelativeLayout) this.findViewById(R.id.rl_weekly_report_light);
        iv_weekly_report_light = (ImageView) this.findViewById(R.id.iv_weekly_report_light);
        rl_weekly_report_light.setOnClickListener(this);


        iv_weekly_report_battery = (ImageView) this.findViewById(R.id.iv_weekly_report_battery);
        iv_weekly_report_battery.setImageResource(R.mipmap.b0);
        bt_add = (Button) this.findViewById(R.id.bt_add);
        bt_add.setOnClickListener(this);
        //which=1本周完成情况
        //which=2下周计划
        //which=3需要师兄师姐支持的内容
        //which=4本周重要bug
        if (which == 1) {
            tv_weekly_report_edit.setText("新增一条本周完成情况:");
            rl_weekly_report_battery.setVisibility(View.VISIBLE);
            //PopupWindow展开列表布局
            popView = LayoutInflater.from(this).inflate(R.layout.weekly_report_edit_popview, null);
            pop_home_rl = (RelativeLayout) popView.findViewById(R.id.pop_home_rl);
            pop_msg_rl = (RelativeLayout) popView.findViewById(R.id.pop_msg_rl);
            pop_share_rl = (RelativeLayout) popView.findViewById(R.id.pop_share_rl);
            pop_share1_rl = (RelativeLayout) popView.findViewById(R.id.pop_share1_rl);
            pop_share2_rl = (RelativeLayout) popView.findViewById(R.id.pop_share2_rl);
            pop_home_rl.setOnClickListener(this);
            pop_msg_rl.setOnClickListener(this);
            pop_share_rl.setOnClickListener(this);
            pop_share1_rl.setOnClickListener(this);
            pop_share2_rl.setOnClickListener(this);
            //计算popupWindow宽度
            width = Density.dip2px(this, 95);
        } else if (which == 2) {
            tv_weekly_report_edit.setText("新增一条下周计划:");
        } else if (which == 3) {
            tv_weekly_report_edit.setText("新增一条需要师兄师姐支持的内容:");
        } else if (which == 4) {
            tv_weekly_report_edit.setText("新增一条本周重要bug解决情况:");
            rl_weekly_report_light.setVisibility(View.VISIBLE);
            iv_weekly_report_light.setImageResource(R.drawable.btn_unlight);

        }
    }


    public void clean_my_weekly_report_et(View view) {
        et_weekly_report_edit.clearFocus();
        et_weekly_report_edit.setText("");
    }

    public void add_a_weekly_report_brackets(View view) {
        et_weekly_report_edit.clearFocus();
        et_weekly_report_edit.setText("【】");
        et_weekly_report_edit.setSelection(1);//将光标移至项目中
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                String str_content_weekly_report_edit = et_weekly_report_edit.getText().toString().trim();
                Log.e("content", str_content_weekly_report_edit);
                //跳转到周报页面
                Intent intent = new Intent();

                intent.putExtra("et_content", str_content_weekly_report_edit);
                intent.putExtra("et_status", status);
                intent.putExtra("et_flag", mFlag);
                intent.putExtra("which", which);

                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.rl_weekly_report_battery:
                if (popupWindow == null) {
                    popupWindow = new PopupWindow(popView, width, WindowManager.LayoutParams.WRAP_CONTENT, true);
                }
                popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.weekly_report_edit_pop_bg));
                popupWindow.setFocusable(true);
                popupWindow.showAsDropDown(rl_weekly_report_battery, -Density.dip2px(this, 55), 0);
                break;
            case R.id.pop_home_rl:
                status = 0;
                popupWindow.dismiss();
                iv_weekly_report_battery.setImageResource(R.mipmap.b0);
                break;
            case R.id.pop_msg_rl:
                status = 1;
                popupWindow.dismiss();
                iv_weekly_report_battery.setImageResource(R.mipmap.b1);
                break;
            case R.id.pop_share_rl:
                status = 2;
                popupWindow.dismiss();
                iv_weekly_report_battery.setImageResource(R.mipmap.b2);
                break;

            case R.id.pop_share1_rl:
                status = 3;
                popupWindow.dismiss();
                iv_weekly_report_battery.setImageResource(R.mipmap.b3);
                break;
            case R.id.pop_share2_rl:
                status = 4;
                popupWindow.dismiss();
                iv_weekly_report_battery.setImageResource(R.mipmap.b4);
                break;
            case R.id.rl_weekly_report_light:
                if (mFlag == false) {
                    mFlag = true;
                    iv_weekly_report_light.setImageResource(R.drawable.btn_light);
                } else {
                    mFlag = false;
                    iv_weekly_report_light.setImageResource(R.drawable.btn_unlight);
                }
                break;
        }
    }


}
