package com.tekinarslan.material.sample.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.util.SQLiteHelper;

public class LoginActivity extends Activity implements OnClickListener {
    EditText et;
    RelativeLayout rl_return, rl_save;
    Button bt_1, bt_2, bt_3, bt_4, bt_5, bt_6, bt_7, bt_8, bt_9, bt_0,
            complete, rollback;

    StringBuilder builder;
    String str;
    String str_which;

    SQLiteDatabase db;
    private static String DB_NAME = "data.db";
    private static int DB_VERSION = 1;
    SQLiteHelper dbHelper;
    // String db_password;
    Cursor cursor;

    ImageView lock, iv_pwd, unlock;
    ImageButton ib_pwd_back;
    String sp_user_pwd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题栏，必须要放在setContentView之前
        setContentView(R.layout.pwd);
        findViewById();

        et.setInputType(InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        lock.setVisibility(View.VISIBLE);
        unlock.setVisibility(View.GONE);

        Intent intent = getIntent();
        str_which = intent.getStringExtra("which");
        builder = new StringBuilder();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_pwd_back:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                this.finish();
                break;

            case R.id.rollback:
                builder.delete(0, builder.length());
                str = builder.toString();
                et.setText("");
                break;
            case R.id.complete:
                String password = et.getText().toString();
                // SQLQueryPwd();
                SharedPreferences sp = getSharedPreferences("user_password", 0);
                sp_user_pwd = sp.getString("user_password", "");// 如果取不到值就取后面的""

                if (password != null && password.equals(sp_user_pwd)) {
                    Toast.makeText(LoginActivity.this, "密码正确！", Toast.LENGTH_SHORT)
                            .show();
                    Intent intent = new Intent(LoginActivity.this,
                            CheckActivity.class);
                    intent.putExtra("which", str_which);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } else {
                    Toast.makeText(LoginActivity.this, "密码错误！", Toast.LENGTH_SHORT)
                            .show();
                    builder.delete(0, builder.length());
                    str = builder.toString();
                    et.setText("");
                    et.setSelection(et.getText().length());
                }
                break;
            case R.id.lock:
                lock.setVisibility(View.INVISIBLE);
                unlock.setVisibility(View.VISIBLE);
                // 密码可见
                et.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
                break;
            case R.id.unlock:
                lock.setVisibility(View.VISIBLE);
                unlock.setVisibility(View.INVISIBLE);
                et.setInputType(InputType.TYPE_CLASS_NUMBER
                        | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                break;

            case R.id.iv_pwd:
                SharedPreferences sp2 = this.getSharedPreferences("first_pref",
                        Context.MODE_PRIVATE);
                Editor editor = sp2.edit(); // 获取编辑器
                editor.putBoolean("isFirstIn", false);
                editor.commit();

                Intent intent3 = new Intent(this, PwdActivity.class);
                startActivity(intent3);
                this.finish();
                break;

            case R.id.bt_1:
                builder.append("1");
                str = builder.toString();
                if (str != "") {
                    et.setText(str);
                    et.setSelection(et.getText().length());
                }
                break;
            case R.id.bt_2:
                builder.append("2");
                str = builder.toString();
                if (str != "") {
                    et.setText(str);
                    et.setSelection(et.getText().length());
                }
                break;
            case R.id.bt_3:
                builder.append("3");
                str = builder.toString();
                if (str != "") {
                    et.setText(str);
                    et.setSelection(et.getText().length());
                }
                break;
            case R.id.bt_4:
                builder.append("4");
                str = builder.toString();
                if (str != "") {
                    et.setText(str);
                    et.setSelection(et.getText().length());
                }
                break;
            case R.id.bt_5:
                builder.append("5");
                str = builder.toString();
                if (str != "") {
                    et.setText(str);
                    et.setSelection(et.getText().length());
                }
                break;
            case R.id.bt_6:
                builder.append("6");
                str = builder.toString();
                if (str != "") {
                    et.setText(str);
                    et.setSelection(et.getText().length());
                }
                break;
            case R.id.bt_7:
                builder.append("7");
                str = builder.toString();
                if (str != "") {
                    et.setText(str);
                    et.setSelection(et.getText().length());
                }
                break;
            case R.id.bt_8:
                builder.append("8");
                str = builder.toString();
                if (str != "") {
                    et.setText(str);
                    et.setSelection(et.getText().length());
                }
                break;
            case R.id.bt_9:
                builder.append("9");
                str = builder.toString();
                if (str != "") {
                    et.setText(str);
                    et.setSelection(et.getText().length());
                }
                break;
            case R.id.bt_0:
                builder.append("0");
                str = builder.toString();
                if (str != "") {
                    et.setText(str);
                    et.setSelection(et.getText().length());
                }
                break;

            default:
                break;
        }

    }

    private void findViewById() {

        et = (EditText) this.findViewById(R.id.et);
        bt_1 = (Button) this.findViewById(R.id.bt_1);
        bt_2 = (Button) this.findViewById(R.id.bt_2);
        bt_3 = (Button) this.findViewById(R.id.bt_3);
        bt_4 = (Button) this.findViewById(R.id.bt_4);
        bt_5 = (Button) this.findViewById(R.id.bt_5);
        bt_6 = (Button) this.findViewById(R.id.bt_6);
        bt_7 = (Button) this.findViewById(R.id.bt_7);
        bt_8 = (Button) this.findViewById(R.id.bt_8);
        bt_9 = (Button) this.findViewById(R.id.bt_9);
        bt_0 = (Button) this.findViewById(R.id.bt_0);

        complete = (Button) this.findViewById(R.id.complete);
        rollback = (Button) this.findViewById(R.id.rollback);

        lock = (ImageView) this.findViewById(R.id.lock);
        lock.setOnClickListener(this);
        unlock = (ImageView) this.findViewById(R.id.unlock);
        unlock.setOnClickListener(this);

        ib_pwd_back = (ImageButton) this.findViewById(R.id.ib_pwd_back);
        ib_pwd_back.setOnClickListener(this);
        iv_pwd = (ImageView) this.findViewById(R.id.iv_pwd);
        iv_pwd.setOnClickListener(this);

        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_0.setOnClickListener(this);
        rollback.setOnClickListener(this);
        complete.setOnClickListener(this);

    }

}
