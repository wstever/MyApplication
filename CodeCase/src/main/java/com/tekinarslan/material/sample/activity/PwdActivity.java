package com.tekinarslan.material.sample.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tekinarslan.material.sample.R;

public class PwdActivity extends Activity implements OnClickListener {

    ImageButton ib_edit_pwd_back;
    EditText et_after_pwd, et_after_pwd_check, et_before_pwd;
    RelativeLayout rl_return, rl_save;
    String user_password, check_user_password, before_user_password;
    String sp_user_pwd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题栏，必须要放在setContentView之前
        setContentView(R.layout.edit_pwd);
        findViewById();

        et_before_pwd.setInputType(InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        et_after_pwd_check.setInputType(InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        et_after_pwd.setInputType(InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

    }

    private void findViewById() {
        ib_edit_pwd_back = (ImageButton) this.findViewById(R.id.ib_edit_pwd_back);
        ib_edit_pwd_back.setOnClickListener(this);

        et_after_pwd = (EditText) this.findViewById(R.id.et_after_pwd);
        et_after_pwd_check = (EditText) this.findViewById(R.id.et_after_pwd_check);
        et_before_pwd = (EditText) this.findViewById(R.id.et_before_pwd);

        rl_return = (RelativeLayout) this.findViewById(R.id.rl_return);
        rl_save = (RelativeLayout) this.findViewById(R.id.rl_save);
        rl_return.setOnClickListener(this);
        rl_save.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_return:
                Intent intent = new Intent(PwdActivity.this, LoginActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.ib_edit_pwd_back:
                Intent intent2 = new Intent(PwdActivity.this, LoginActivity.class);
                startActivity(intent2);
                this.finish();
                break;
            case R.id.rl_save:
                save();
                break;
            default:
                break;
        }
    }

    private void save() {
        before_user_password = et_before_pwd.getText().toString().trim();
        check_user_password = et_after_pwd_check.getText().toString().trim();
        user_password = et_after_pwd.getText().toString().trim();

        if (before_user_password.length() != 0) {
            if (check_user_password.length() != 0) {
                if (user_password.length() != 0) {

                    SharedPreferences sp1 = getSharedPreferences(
                            "user_password", 0);
                    sp_user_pwd = sp1.getString("user_password", "");// 如果取不到值就取后面的""
                    Log.d("sp_user_pwd", sp_user_pwd);
                    Log.d("before_user_password", before_user_password);
                    if (before_user_password.equals(sp_user_pwd)) {

                        if (check_user_password.equals(user_password)) {
                            // 实例化SharedPreferences对象
                            SharedPreferences sp = getSharedPreferences(
                                    "user_password", Activity.MODE_PRIVATE);
                            // 实例化SharedPreferences.Editor对象
                            SharedPreferences.Editor editor = sp.edit();
                            // 用putString的方法保存数据
                            editor.putString("user_password",
                                    check_user_password);
                            // 提交当前数据
                            editor.commit();
                            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT)
                                    .show();
                            Intent intent = new Intent(PwdActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                            this.finish();

                        } else {
                            Toast.makeText(this, "两次密码输入不相同",
                                    Toast.LENGTH_SHORT).show();
                            et_before_pwd.setText("");
                            et_after_pwd_check.setText("");
                            et_after_pwd.setText("");
                        }
                    } else {
                        Toast.makeText(this, "请输入正确的原始密码", Toast.LENGTH_SHORT)
                                .show();
                        et_before_pwd.setText("");
                        et_after_pwd_check.setText("");
                        et_after_pwd.setText("");
                    }
                }
            }
        }
    }
}
