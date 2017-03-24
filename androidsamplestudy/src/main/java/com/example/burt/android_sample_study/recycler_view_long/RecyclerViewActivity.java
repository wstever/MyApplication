package com.example.burt.android_sample_study.recycler_view_long;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.burt.android_sample_study.R;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> datas;
    private MyAdapter adapter;
    private Button btn1;
    private Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.long_rv_activity_recycler);
            getData();
            mRecyclerView = (RecyclerView) findViewById(R.id.listview_recy);
            btn1 = (Button) findViewById(R.id.btn1);
            btn2 = (Button) findViewById(R.id.btn2);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new MyAdapter();
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);

        //item的点击事件
        adapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String s = datas.get(position);
                Toast.makeText(getApplicationContext(), s + "---点击", Toast.LENGTH_SHORT).show();
            }
        });

        //item的长按事件
        adapter.setOnLongItemClickListener(new OnRecyclerViewLongItemClickListener() {
            @Override
            public void onLongItemClick(View view, int position) {
                String s = datas.get(position);
                Toast.makeText(getApplicationContext(), s + "---长按", Toast.LENGTH_SHORT).show();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas.size() > 4) {
                    datas.add(4, "我是加的");
                    adapter.notifyItemInserted(4);
//                    adapter.notifyItemRangeChanged(2, 2);//刷新从position以后的2条数据
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas.size() > 6) {
                    datas.remove(6);
                    adapter.notifyItemRemoved(6);
                }
            }
        });
    }

    private void getData() {
        datas = new ArrayList<>();
        for (int i = 0; i <= 26; i++) {
            datas.add(i + " ");
        }
    }

    public interface OnRecyclerViewLongItemClickListener {
        void onLongItemClick(View view, int position);
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        public OnRecyclerViewItemClickListener mOnItemClickListener = null;//点击
        public OnRecyclerViewLongItemClickListener mOnLongItemClickListener = null;//长按

        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }

        public void setOnLongItemClickListener(OnRecyclerViewLongItemClickListener listener) {
            this.mOnLongItemClickListener = listener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.long_rv_activtiy_recycler_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.tv1.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv1;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv1 = (TextView) itemView.findViewById(R.id.tv_week_num);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(v, getAdapterPosition());
                        }
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (mOnLongItemClickListener != null) {
                            mOnLongItemClickListener.onLongItemClick(v, getAdapterPosition());
                        }
                        return true;
                    }
                });
            }
        }
    }
}
