package com.example.burt.lab.project_report;

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

import com.example.burt.lab.R;
import com.example.burt.lab.util.Density;


public class ProjectReportEditActivity extends Activity implements
        View.OnClickListener {


    private RelativeLayout rl_project_report_battery, rl_project_report_light;
    private RelativeLayout pop_home_rl, pop_msg_rl, pop_share_rl, pop_share1_rl, pop_share2_rl;

    private TextView tv_project_report_edit;
    private ImageView iv_project_report_battery, iv_project_report_light;
    private Button bt_add;
    private EditText et_project_report_edit;
    private String str_content_project_report_edit;
    //下拉展开按钮
    private PopupWindow popupWindow;
    private View popView;
    //进度

    private int status;
    //下拉列表宽度
    private int width;

    private int which;
    private int get_position;
    private String original_content;
    private Boolean mFlag = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_report_edit_activity);
        Intent intent = getIntent();
        which = intent.getIntExtra("which", 0);
        get_position = intent.getIntExtra("get_position", -1);
        original_content = intent.getStringExtra("original_content");
        initView();
    }

    public void initView() {
        //电池按钮
        rl_project_report_battery = (RelativeLayout) this.findViewById(R.id.rl_project_report_battery);
        rl_project_report_battery.setOnClickListener(this);
        et_project_report_edit = (EditText) this.findViewById(R.id.et_project_report_edit);
        //排除是点击新增按钮进来的情况
        if (original_content != null) {
            et_project_report_edit.setText(original_content);
            et_project_report_edit.setSelection(original_content.length());//将光标移至文字末尾
        }


        tv_project_report_edit = (TextView) this.findViewById(R.id.tv_project_report_edit);
        //进度描述


        //bug解决了还是进行中
        //   rl_project_report_light = (RelativeLayout) this.findViewById(R.id.rl_project_report_light);
        // iv_project_report_light = (ImageView) this.findViewById(R.id.iv_project_report_light);
        //rl_project_report_light.setOnClickListener(this);


        iv_project_report_battery = (ImageView) this.findViewById(R.id.iv_project_report_battery);
        iv_project_report_battery.setImageResource(R.mipmap.b0);
        bt_add = (Button) this.findViewById(R.id.bt_add);
        bt_add.setOnClickListener(this);
        //which=1本周完成情况
        //which=2下周计划
        //which=3需要师兄师姐支持的内容

        if (which == 1) {
            tv_project_report_edit.setText("新增一条本周完成情况:");
            rl_project_report_battery.setVisibility(View.VISIBLE);
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
            tv_project_report_edit.setText("新增一条下周计划:");
        } else if (which == 3) {
            tv_project_report_edit.setText("新增一条项目中需要师兄师姐支持的内容:");
        }
    }


    public void clean_my_project_et(View view){
        et_project_report_edit.clearFocus();
        et_project_report_edit.setText("");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                String str_content_project_report_edit = et_project_report_edit.getText().toString().trim();
                Log.e("content", str_content_project_report_edit);
                //跳转到项目周报页面
                Intent intent = new Intent();
                intent.putExtra("et_content", str_content_project_report_edit);
                intent.putExtra("et_status", status);
                intent.putExtra("et_flag", mFlag);
                intent.putExtra("which", which);
                intent.putExtra("get_position", get_position);
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.rl_project_report_battery:
                if (popupWindow == null) {
                    popupWindow = new PopupWindow(popView, width, WindowManager.LayoutParams.WRAP_CONTENT, true);
                }
                popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.weekly_report_edit_pop_bg));
                popupWindow.setFocusable(true);
                popupWindow.showAsDropDown(rl_project_report_battery, -Density.dip2px(this, 55), 0);
                break;
            case R.id.pop_home_rl:
                status = 0;
                popupWindow.dismiss();
                iv_project_report_battery.setImageResource(R.mipmap.b0);
                break;
            case R.id.pop_msg_rl:
                status = 1;
                popupWindow.dismiss();
                iv_project_report_battery.setImageResource(R.mipmap.b1);
                break;
            case R.id.pop_share_rl:
                status = 2;
                popupWindow.dismiss();
                iv_project_report_battery.setImageResource(R.mipmap.b2);
                break;

            case R.id.pop_share1_rl:
                status = 3;
                popupWindow.dismiss();
                iv_project_report_battery.setImageResource(R.mipmap.b3);
                break;
            case R.id.pop_share2_rl:
                status = 4;
                popupWindow.dismiss();
                iv_project_report_battery.setImageResource(R.mipmap.b4);
                break;
            //   case R.id.rl_project_report_light:
            //       if (mFlag == false) {
            //            mFlag = true;
            //            iv_project_report_light.setImageResource(R.drawable.btn_light);
            //        } else {
            //            mFlag = false;
            //             iv_project_report_light.setImageResource(R.drawable.btn_unlight);
            //         }
            //         break;
        }
    }
}
