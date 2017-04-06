package com.example.burt.lab.weekly_report;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.burt.lab.R;

import java.util.List;


public class WeeklyReportRvAdapter extends RecyclerView.Adapter<WeeklyReportRvAdapter.ViewHolder> {
    private List<WeeklyReportCustomRvBean> items;
    private LayoutInflater mLayoutInflater;


    private WeeklyReportRvAdapter.OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    public void setOnItemClickListener(WeeklyReportRvAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public WeeklyReportRvAdapter(List<WeeklyReportCustomRvBean> items) {
        super();
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_weekly_report_finish;
        TextView tv_weekly_report_plan;
        TextView tv_weekly_report_time;
        TextView tv_weekly_report_progress;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_weekly_report_finish = (TextView) itemView.findViewById(R.id.tv_weekly_report_finish);
            tv_weekly_report_plan = (TextView) itemView.findViewById(R.id.tv_weekly_report_plan);
            tv_weekly_report_time = (TextView) itemView.findViewById(R.id.tv_weekly_report_time);
            tv_weekly_report_progress = (TextView) itemView.findViewById(R.id.tv_weekly_report_progress);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = mLayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weekly_report_custom_rv_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        WeeklyReportCustomRvBean item = items.get(position);
        viewHolder.tv_weekly_report_finish.setText(item.getWeekly_report_finish());
        viewHolder.tv_weekly_report_plan.setText(item.getWeekly_report_plan());
        viewHolder.tv_weekly_report_progress.setText(item.getWeekly_report_progress());
        viewHolder.tv_weekly_report_time.setText(item.getWeekly_report_time());
        //如果设置了回调则设施点击事件
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.tv_weekly_report_plan, pos);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(viewHolder.tv_weekly_report_plan, pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
