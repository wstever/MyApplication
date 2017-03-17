package com.tekinarslan.material.sample.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.CodeCaseBean;
import com.tekinarslan.material.sample.util.SQLiteHelper;

public class CheckActivity extends Activity implements OnClickListener {
	EditText et_modify_password;
	TextView tv_check_name, tv_check_username, tv_check_password;
	RelativeLayout rl_check_return, rl_check_modify;
	String str_modify_password;
	TextView tv_state;
	String str_which;
	TextView title_name;

	SQLiteDatabase db;
	private static String DB_NAME = "data.db";
	private static int DB_VERSION = 1;
	SQLiteHelper dbHelper;
	String db_password, db_username, db_name = "", db_id;
	Cursor cursor;

	ImageButton ib_check_code_back;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题栏，必须要放在setContentView之前
		setContentView(R.layout.check_code);
		findViewById();

		Intent intent = getIntent();
		str_which = intent.getStringExtra("which");
		getOtherFromDB();
		showThemToTV();

		title_name.setText("查看密码");
		tv_state.setText("修改");
		et_modify_password.setVisibility(View.INVISIBLE);
		tv_check_password.setVisibility(View.VISIBLE);

	}

	private void showThemToTV() {
		if (db_name.length() >= 1) {
			if (db_username.length() >= 1) {
				if (db_password.length() >= 1) {
					tv_check_name.setText(db_name);
					tv_check_password.setText(db_password);
					tv_check_username.setText(db_username);
				}
			}
		}
	}

	private void getOtherFromDB() {
		dbHelper = new SQLiteHelper(this, DB_NAME, null, DB_VERSION);
		db = dbHelper.getReadableDatabase();
		String sql = "select * from " + SQLiteHelper.TB_NAME + " where "
				+ CodeCaseBean.NAME + "= '" + str_which + "'";
		cursor = db.rawQuery(sql, new String[0]);
		while (cursor.moveToNext()) {
			db_name = cursor.getString(cursor.getColumnIndex("name"));
			db_username = cursor.getString(cursor.getColumnIndex("username"));
			db_password = cursor.getString(cursor.getColumnIndex("password"));
		}
	}

	private void findViewById() {
		tv_state = (TextView) findViewById(R.id.tv_state);
		title_name = (TextView) findViewById(R.id.title_name);

		tv_check_name = (TextView) findViewById(R.id.tv_check_name);
		tv_check_username = (TextView) findViewById(R.id.tv_check_username);
		tv_check_password = (TextView) findViewById(R.id.tv_check_password);

		et_modify_password = (EditText) findViewById(R.id.et_modify_password);

		rl_check_return = (RelativeLayout) findViewById(R.id.rl_check_return);
		rl_check_modify = (RelativeLayout) findViewById(R.id.rl_check_modify);
		rl_check_return.setOnClickListener(this);
		rl_check_modify.setOnClickListener(this);

		ib_check_code_back = (ImageButton) this
				.findViewById(R.id.ib_check_code_back);
		ib_check_code_back.setOnClickListener(this);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_check_return:
			Intent intent = new Intent(CheckActivity.this, MainActivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.rl_check_modify:

			title_name.setText("修改密码");
			tv_state.setText("保存");

			et_modify_password.setVisibility(View.VISIBLE);
			tv_check_password.setVisibility(View.INVISIBLE);

			modify();
			break;
		case R.id.ib_check_code_back:
			Intent intent2 = new Intent(CheckActivity.this, MainActivity.class);
			startActivity(intent2);
			this.finish();
			break;
		default:
			break;
		}

	}

	private void modify() {
		str_modify_password = et_modify_password.getText().toString().trim();
		if (str_modify_password.length() > 0) {
			dbHelper = new SQLiteHelper(this, DB_NAME, null, DB_VERSION);
			db = dbHelper.getReadableDatabase();
			ContentValues values = new ContentValues();
			values.put(CodeCaseBean.PASSWORD, str_modify_password);
			// 第一个参数是要更新的表名
			// 第二个参数是一个ContentValeus对象
			// 第三个参数是where子句
			String where = CodeCaseBean.NAME + "= '" + str_which + "'";
			db.update(SQLiteHelper.TB_NAME, values, where, null);

			Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();

			title_name.setText("查看密码");
			tv_state.setText("修改");
			et_modify_password.setVisibility(View.INVISIBLE);
			et_modify_password.setText("");
			tv_check_password.setVisibility(View.VISIBLE);
			tv_check_password.setText(str_modify_password);
		}
	}
}
