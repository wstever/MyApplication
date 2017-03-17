package com.tekinarslan.material.sample.activity;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.CodeCaseBean;
import com.tekinarslan.material.sample.custom.SystemBarTintManager;
import com.tekinarslan.material.sample.util.SQLiteHelper;

import java.util.Random;

public class NewCheckActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_username, et_name, et_password;
    private Button bt_cancel, bt_save;
    private TextInputLayout mTextInputLayoutName;
    private TextInputLayout mTextInputLayoutPswd;
    String str_name, str_username, str_password;
    private static String DB_NAME = "data.db";
    private static int DB_VERSION = 1;
    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    Cursor cursor;
    String db_name = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.orange_normal);//通知栏所需颜色
        }
        setContentView(R.layout.code_case_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("新加账号密码");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        findViewById();
        getEditTextData();
        initDB();
    }


    private boolean checkPswd(CharSequence pswd, boolean isLogin) {
        if (TextUtils.isEmpty(pswd)) {
            if (isLogin) {
                mTextInputLayoutPswd.setError(getString(R.string.error_pswd_empty));
                return false;
            }
        } else {
            mTextInputLayoutPswd.setError(null);
        }
        return true;
    }

    private boolean checkName(CharSequence name, boolean isLogin) {
        if (TextUtils.isEmpty(name)) {
            if (isLogin) {
                mTextInputLayoutName.setError(getString(R.string.error_login_empty));
                return false;
            }
        } else {
            mTextInputLayoutName.setError(null);
        }
        return true;
    }

    private void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    private void initDB() {
        dbHelper = new SQLiteHelper(this, DB_NAME, null, DB_VERSION);
        db = dbHelper.getWritableDatabase();
        // db = dbHelper.getReadableDatabase();
    }

    private void getEditTextData() {
        str_name = et_name.getText().toString().trim();
        str_username = et_username.getText().toString().trim();
        str_password = et_password.getText().toString().trim();
    }

    private void findViewById() {
        et_name = (EditText) this.findViewById(R.id.et_name);
        et_username = (EditText) this.findViewById(R.id.et_username);
        et_password = (EditText) this.findViewById(R.id.et_password);
        mTextInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        mTextInputLayoutPswd = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        mTextInputLayoutName.setErrorEnabled(true);
        mTextInputLayoutPswd.setErrorEnabled(true);
        bt_save = (Button) findViewById(R.id.bt_save);
        bt_save.setOnClickListener(this);
        bt_cancel = (Button) findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(this);
        et_username.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                checkName(s.toString(), false);
            }
        });
        et_name.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                checkPswd(s.toString(), false);
            }
        });
        et_password.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                checkPswd(s.toString(), false);
            }
        });


    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cancel:
                Intent intent = new Intent(NewCheckActivity.this, SampleActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.bt_save:
                hideKeyBoard();
                save();
                break;
            default:
                break;
        }
    }


    private void save() {
        if (et_name.getText().length() >= 1) {
            if (et_username.getText().length() >= 1) {
                if (et_password.getText().length() >= 1) {
                    checkSameName();
                    if (db_name.equals(et_name.getText().toString().trim())) {
                        Toast.makeText(getApplicationContext(), "該名称已存在",
                                Toast.LENGTH_SHORT).show();
                        et_name.setText("");
                        et_username.setText("");
                        et_password.setText("");
                    } else {
                        insert();
                        Intent intent2 = new Intent(NewCheckActivity.this, SampleActivity.class);
                        startActivity(intent2);
                    }
                } else {
                    Toast.makeText(this, "请输入您的密码", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "请输入您的账号", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请输入您的应用名称", Toast.LENGTH_SHORT).show();
        }
    }

    private void insert() {
        ContentValues values = new ContentValues();
        Random ran = new Random();
        int num = ran.nextInt(50000);
        values.put(CodeCaseBean.ID, num);
        values.put(CodeCaseBean.NAME, et_name.getText().toString().trim());
        values.put(CodeCaseBean.USERNAME, et_username.getText().toString()
                .trim());
        values.put(CodeCaseBean.PASSWORD, et_password.getText().toString()
                .trim());

        Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT)
                .show();
        // 插入数据 用ContentValues对象也即HashMap操作,并返回ID号
        db.insert(SQLiteHelper.TB_NAME, CodeCaseBean.ID, values);
        // 重置为空
        et_name.setText("");
        et_username.setText("");
        et_password.setText("");
    }

    private void checkSameName() {
        dbHelper = new SQLiteHelper(this, DB_NAME, null, DB_VERSION);
        db = dbHelper.getReadableDatabase();
        String sql = "select * from " + SQLiteHelper.TB_NAME + " where "
                + CodeCaseBean.NAME + "= '"
                + et_name.getText().toString().trim() + "'";
        cursor = db.rawQuery(sql, new String[0]);
        while (cursor.moveToNext()) {
            db_name = cursor.getString(cursor.getColumnIndex("name"));
        }
    }
}
