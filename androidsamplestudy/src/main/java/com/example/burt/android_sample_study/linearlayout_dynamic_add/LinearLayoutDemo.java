package com.example.burt.android_sample_study.linearlayout_dynamic_add;


import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.burt.android_sample_study.R;

public class LinearLayoutDemo extends ListActivity {

    private ListAddLayoutAdapter mAdapter;
    private LayoutInflater lInflater;

    int[] image = {
            R.mipmap.b0,
            R.mipmap.b1,
            R.mipmap.b2,
            R.mipmap.b3,
            R.mipmap.b4
    };

    String[] show_name = {
            "贝贝",
            "晶晶",
            "欢欢",
            "迎迎",
            "妮妮"
    };

    private int id = 0;
    private int last = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ListAddLayoutAdapter(this);
        this.setListAdapter(mAdapter);

        this.getListView().setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                id = position;
                mAdapter.notifyDataSetChanged();
                last = position;
            }
        });
    }

    public class ListAddLayoutAdapter extends BaseAdapter {

        private Context context;
        private LinearLayoutDemo activity;

        public ListAddLayoutAdapter(Context context) {
            this.context = context;
            this.activity = (LinearLayoutDemo) context;
            lInflater = activity.getLayoutInflater();
        }

        public int getCount() {
            return image.length;
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View arg1, ViewGroup arg2) {
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(0, 8, 0, 8);
            layout.addView(addTitleView(position));
            if (id == position) {
                layout.addView(addCustomView(position));
            }

            return layout;
        }

        public View addTitleView(int i) {
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.HORIZONTAL);

            ImageView iv = new ImageView(context);
            iv.setImageResource(image[i]);
            layout.addView(iv,
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));


            TextView tv = new TextView(context);
            tv.setText(show_name[i]);
            tv.setTextSize(18f);
            layout.addView(tv,
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

            layout.setGravity(Gravity.CENTER);
            return layout;
        }


        public View addCustomView(int i) {
            View view = new View(context);

            switch (i) {
                case 0:
                    ImageView iv1 = new ImageView(context);
                    iv1.setImageResource(R.mipmap.b0);
                    view = iv1;
                    break;

                case 1:
                    ImageView iv2 = new ImageView(context);
                    iv2.setImageResource(R.mipmap.b1);
                    view = iv2;
                    break;

                case 2:
                    ImageView iv3 = new ImageView(context);
                    iv3.setImageResource(R.mipmap.b2);
                    view = iv3;
                    break;
                case 3:
                    ImageView iv4 = new ImageView(context);
                    iv4.setImageResource(R.mipmap.b3);
                    view = iv4;
                    break;
                case 4:
                    ImageView iv5 = new ImageView(context);
                    iv5.setImageResource(R.mipmap.b4);
                    view = iv5;
                    break;
            }
            return view;
        }
    }
}