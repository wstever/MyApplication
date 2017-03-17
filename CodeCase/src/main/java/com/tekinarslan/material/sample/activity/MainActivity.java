package com.tekinarslan.material.sample.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.CodeCaseBean;
import com.tekinarslan.material.sample.util.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements OnClickListener {

    ListView lv;
    TextView tv_nothing, tv_addsth;
    ImageView iv_box, iv_add;
    AlertDialog dialog;
    TextView tv_cancel, tv_sure;

    private ListAdapter adapter = null;
    private List<CodeCaseBean> tableList = new ArrayList<CodeCaseBean>();

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private static String DB_NAME = "data.db";
    private static int DB_VERSION = 1;
    Cursor cursor;

    boolean isFirstIn = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题栏，必须要放在setContentView之前
        setContentView(R.layout.list);



        getList();


        findViewById();
        adapter = new ListAdapter(this);
        setListAdapter(adapter);
        getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView parent, View view,
                                           int position, long id) {
                String str_name = tableList.get(position).getName();
                dialog(position, str_name);
                return true;
            }
        });
        tableListSize();

        SharedPreferences sp2 = getSharedPreferences("first_pref", MODE_PRIVATE);
        isFirstIn = sp2.getBoolean("isFirstIn", true);
        if (isFirstIn) {
            // 实例化SharedPreferences对象
            SharedPreferences sp = getSharedPreferences("user_password",
                    Activity.MODE_PRIVATE);
            // 实例化SharedPreferences.Editor对象
            SharedPreferences.Editor editor = sp.edit();
            // 用putString的方法保存数
            editor.putString("user_password", "1234");
            // 提交当前数据
            editor.commit();
        }

    }

    private void tableListSize() {
        if (tableList.size() != 0) {
            iv_box.setVisibility(View.GONE);
            tv_addsth.setVisibility(View.GONE);
            tv_nothing.setVisibility(View.GONE);
        } else {
            iv_box.setVisibility(View.VISIBLE);
            tv_addsth.setVisibility(View.VISIBLE);
            tv_nothing.setVisibility(View.VISIBLE);
        }
    }

    private void dialog(final int position, final String str_name) {
        // 1. 布局文件转换为View对象
        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(
                R.layout.alert_dialog, null);

        // 2. 新建对话框对
        final Dialog dialog = new AlertDialog.Builder(MainActivity.this)
                .create();
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().setContentView(layout);

        // 3. 消息内容
        TextView dialog_msg = (TextView) layout.findViewById(R.id.dialog_msg);
        dialog_msg.setText("确定要删除？");
        Button btnOK = (Button) layout.findViewById(R.id.dialog_ok);
        btnOK.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "ok",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                tableList.remove(position);
                removeFromDB(str_name);
                tableListSize();
                adapter.notifyDataSetChanged();
            }
        });

        Button btnCancel = (Button) layout.findViewById(R.id.dialog_cancel);
        btnCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "cancel",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private void getList() {
        dbHelper = new SQLiteHelper(this, DB_NAME, null, DB_VERSION);
        db = dbHelper.getReadableDatabase();

        // 查询表，得到cursor对象
        cursor = db.query(SQLiteHelper.TB_NAME, null, null, null, null, null,
                CodeCaseBean.NAME + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast() && (cursor.getString(1) != null)) {
            CodeCaseBean entity = new CodeCaseBean();
            entity.setId(cursor.getString(0));
            entity.setName(cursor.getString(1));
            entity.setUsername(cursor.getString(2));
            entity.setPassword(cursor.getString(3));
            tableList.add(entity);
            cursor.moveToNext();
        }
    }

    private void removeFromDB(String str_name) {
        dbHelper = new SQLiteHelper(this, DB_NAME, null, DB_VERSION);
        db = dbHelper.getWritableDatabase();
        db.delete(SQLiteHelper.TB_NAME, CodeCaseBean.NAME + "='" + str_name
                + "'", null);
    }

    public class ViewHolder {
        public ImageView iv_check;
        public TextView tv_name;
    }

    public class ListAdapter extends BaseAdapter {
        private LayoutInflater inflater = null;

        public int getCount() {
            return tableList.size();
        }

        public ListAdapter(Context context) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public Object getItem(int position) {
            return tableList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ViewHolder holder = null;

            if (convertView == null) {
                holder = new ViewHolder();

                convertView = inflater.inflate(R.layout.item, null);
                holder.iv_check = (ImageView) convertView
                        .findViewById(R.id.iv_check);
                holder.tv_name = (TextView) convertView
                        .findViewById(R.id.tv_name);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            // MyEntity entity = list_detail.get(position);
            // if (entity != null) {
            holder.tv_name.setText(tableList.get(position).getName());
            holder.iv_check.setImageResource(R.drawable.check);
            holder.iv_check.setFocusable(false);
            holder.iv_check.setFocusableInTouchMode(false);
            holder.iv_check.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,
                            LoginActivity.class);
                    intent.putExtra("which", tableList.get(position).getName());
                    startActivity(intent);
                    finish();
                }
            });
            // }

            iv_add.setFocusable(false);
            iv_add.setFocusableInTouchMode(false);
            return convertView;
        }
    }

    ;

    private void findViewById() {
        tv_nothing = (TextView) this.findViewById(R.id.code_case_tv_nothing);
        tv_addsth = (TextView) this.findViewById(R.id.code_case_tv_addsth);
        iv_box = (ImageView) this.findViewById(R.id.code_case_iv_box);
        iv_add = (ImageView) this.findViewById(R.id.code_case_iv_add);
        iv_add.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.code_case_iv_add:
                Intent intent = new Intent(MainActivity.this, NewAddActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }
}
