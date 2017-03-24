package com.example.burt.android_sample_study.weekly_report;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.burt.android_sample_study.R;
import com.example.burt.android_sample_study.recycler_view_long.RecyclerViewActivity;

import java.util.List;

public class WeeklyReportAdapter
        extends RecyclerView.Adapter<WeeklyReportAdapter.ViewHolder> {
    private List<WeeklyReportBean> mData;
    private int which;

    public WeeklyReportAdapter(List<WeeklyReportBean> data, int which) {
        this.mData = data;
        this.which = which;
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(
            OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    //长按
    public OnLongItemClickListener mOnLongItemClickListener;

    //长按
    public void setOnLongItemClickListener(OnLongItemClickListener listener) {
        this.mOnLongItemClickListener = listener;
    }

    //长按
    public interface OnLongItemClickListener {
        void onLongItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView tv_weekly_report_content;
        public RelativeLayout rl_weekly_report_item;
        public TextView tv_weekly_report_count;
        public ImageView iv_weekly_report_item;
        public TextView tv_weekly_report_isSolved;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_weekly_report_content = (TextView) itemView.findViewById(R.id.tv_weekly_report_content);
            rl_weekly_report_item = (RelativeLayout) itemView.findViewById(R.id.rl_weekly_report_item);
            rl_weekly_report_item.setOnClickListener(this);
            tv_weekly_report_count = (TextView) itemView.findViewById(R.id.tv_weekly_report_count);
            iv_weekly_report_item = (ImageView) itemView.findViewById(R.id.iv_weekly_report_item);
            tv_weekly_report_isSolved = (TextView) itemView.findViewById(R.id.tv_weekly_report_isSolved);

        }

        // 通过接口回调来实现RecyclerView的点击事件
        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getPosition());
            }
            if (mOnLongItemClickListener != null) {
                mOnLongItemClickListener.onLongItemClick(v, getPosition());
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 将布局转化为View并传递给RecyclerView封装好的ViewHolder
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.weekly_report_rv_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        // 建立起ViewHolder中视图与数据的关联
        viewHolder.tv_weekly_report_count.setText(i + 1 + "");

        viewHolder.tv_weekly_report_content.setText(mData.get(i).getContent());
        //第一种的,本周完成情况设置
        if (which == 1) {
            viewHolder.iv_weekly_report_item.setVisibility(View.VISIBLE);
            int j = mData.get(i).getStatus();
            switch (j) {
                case 0:
                    viewHolder.iv_weekly_report_item.setImageResource(R.mipmap.b0);
                    break;
                case 1:
                    viewHolder.iv_weekly_report_item.setImageResource(R.mipmap.b1);
                    break;
                case 2:
                    viewHolder.iv_weekly_report_item.setImageResource(R.mipmap.b2);
                    break;
                case 3:
                    viewHolder.iv_weekly_report_item.setImageResource(R.mipmap.b3);
                    break;
                default:
                    viewHolder.iv_weekly_report_item.setImageResource(R.mipmap.b4);
                    break;
            }
        }
        //第二种的,本周重要bug的解决
        if (which == 2) {
            viewHolder.tv_weekly_report_isSolved.setVisibility(View.VISIBLE);
            boolean flag = mData.get(i).isSolved();
            String str_is_solved;
            if (flag == false) {
                str_is_solved = "未解决";
                viewHolder.tv_weekly_report_isSolved.setTextColor(Color.RED);
            } else {
                str_is_solved = "已完成";
                viewHolder.tv_weekly_report_isSolved.setTextColor(Color.GREEN);
            }
            viewHolder.tv_weekly_report_isSolved.setText(str_is_solved);
        }
        //下周计划RV.师兄师姐帮忙RV
        if (which == 3) {
            viewHolder.iv_weekly_report_item.setVisibility(View.GONE);

            viewHolder.tv_weekly_report_isSolved.setVisibility(View.GONE);
        }


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}