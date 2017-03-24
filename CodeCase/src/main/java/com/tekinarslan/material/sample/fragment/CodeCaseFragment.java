package com.tekinarslan.material.sample.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.activity.NewAddActivity;
import com.tekinarslan.material.sample.util.SQLiteHelper;
import com.tekinarslan.material.sample.adapter.CodeCaseRvAdapter;
import com.tekinarslan.material.sample.bean.CodeCaseBean;

import java.util.ArrayList;
import java.util.List;


public class CodeCaseFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int position;
    private RecyclerView rv_show_codecase_list;
    private CodeCaseRvAdapter mCodeCaseRvAdapter;
    private TextView code_case_tv_nothing, code_case_tv_addsth;
    private ImageView code_case_iv_box, code_case_iv_add;
    private List<CodeCaseBean> mCodeCaseList = new ArrayList<CodeCaseBean>();

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private static String DB_NAME = "data.db";
    private static int DB_VERSION = 1;
    Cursor cursor;

    boolean isFirstIn = true;

    public static CodeCaseFragment newInstance(int position) {
        CodeCaseFragment f = new CodeCaseFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    private View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.codecase, container, false);
            initView(rootView);// 控件初始化
        }
        return rootView;
    }

    private void initView(View rootView) {
        getList();
        //如果该列表里面没有东西
        code_case_tv_nothing = (TextView) rootView.findViewById(R.id.code_case_tv_nothing);
        code_case_tv_addsth = (TextView) rootView.findViewById(R.id.code_case_tv_addsth);
        code_case_iv_box = (ImageView) rootView.findViewById(R.id.code_case_iv_box);
        code_case_iv_add = (ImageView) rootView.findViewById(R.id.code_case_iv_add);
        // 给iv_add添加点击事件
        View.OnClickListener listener_add = null;
        listener_add = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewAddActivity.class);
                startActivity(intent);
            }
        };
        code_case_iv_add.setOnClickListener(listener_add);

        rv_show_codecase_list = (RecyclerView) rootView.findViewById(R.id.main_recyclerview);
        rv_show_codecase_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        testListSize();
        //item的长按事件

/*
        rv_show_codecase_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView parent, View view,
                                           int position, long id) {
                String str_name = mCodeCaseList.get(position).getName();
                dialog(position, str_name);
                return true;
            }
        });*/
        /*

        SharedPreferences sp2 = getActivity().getSharedPreferences("first_pref", MODE_PRIVATE);
        isFirstIn = sp2.getBoolean("isFirstIn", true);
        if (isFirstIn) {
            // 实例化SharedPreferences对象
            SharedPreferences sp = getActivity().getSharedPreferences("user_password",
                    MODE_PRIVATE);
            // 实例化SharedPreferences.Editor对象
            SharedPreferences.Editor editor = sp.edit();
            // 用putString的方法保存数
            editor.putString("user_password", "1234");
            // 提交当前数据
            editor.commit();
        }
*/
    }


    private void testListSize() {
        if (mCodeCaseList.size() != 0) {
            code_case_iv_box.setVisibility(View.GONE);
            code_case_tv_addsth.setVisibility(View.GONE);
            code_case_tv_nothing.setVisibility(View.GONE);


            mCodeCaseRvAdapter = new CodeCaseRvAdapter(mCodeCaseList);
            rv_show_codecase_list.setHasFixedSize(true);
            rv_show_codecase_list.setAdapter(mCodeCaseRvAdapter);
            mCodeCaseRvAdapter.setOnItemClickListener(new CodeCaseRvAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, String data) {
                    Toast.makeText(getActivity(), "data:" + data, Toast.LENGTH_SHORT).show();
                }
            });
            mCodeCaseRvAdapter.setOnItemLongClickListener(new CodeCaseRvAdapter.OnItemLongClickListener() {
                @Override
                public void onItemLongClick(View view, String data) {
                    Toast.makeText(getActivity(), "123:" + data, Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            code_case_iv_box.setVisibility(View.VISIBLE);
            code_case_tv_addsth.setVisibility(View.VISIBLE);
            code_case_tv_nothing.setVisibility(View.VISIBLE);
        }
    }


    private void dialog(final int position, final String str_name) {
        // 1. 布局文件转换为View对象
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        RelativeLayout layout = (RelativeLayout) inflater.inflate(
                R.layout.alert_dialog, null);

        // 2. 新建对话框对
        final Dialog dialog = new AlertDialog.Builder(getActivity())
                .create();
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().setContentView(layout);

        // 3. 消息内容
        TextView dialog_msg = (TextView) layout.findViewById(R.id.dialog_msg);
        dialog_msg.setText("确定要删除？");
        Button btnOK = (Button) layout.findViewById(R.id.dialog_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "ok",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                mCodeCaseList.remove(position);
                removeFromDB(str_name);
                testListSize();
                //      adapter.notifyDataSetChanged();
            }
        });

        Button btnCancel = (Button) layout.findViewById(R.id.dialog_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "cancel",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


    }


    private void getList() {
        dbHelper = new SQLiteHelper(getActivity(), DB_NAME, null, DB_VERSION);
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
            mCodeCaseList.add(entity);
            cursor.moveToNext();
        }
    }

    private void removeFromDB(String str_name) {
        dbHelper = new SQLiteHelper(getActivity(), DB_NAME, null, DB_VERSION);
        db = dbHelper.getWritableDatabase();
        db.delete(SQLiteHelper.TB_NAME, CodeCaseBean.NAME + "='" + str_name
                + "'", null);
    }
}