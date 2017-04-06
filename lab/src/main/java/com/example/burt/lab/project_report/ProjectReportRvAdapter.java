package com.example.burt.lab.project_report;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.burt.lab.R;

import java.util.List;


public class ProjectReportRvAdapter extends RecyclerView.Adapter<ProjectReportRvAdapter.ViewHolder> {
    private List<ProjectReportCustomRvBean> items;

    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;

    }


    public ProjectReportRvAdapter(List<ProjectReportCustomRvBean> items) {
        super();
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_project_report_finish;
        TextView tv_project_report_time;
        TextView tv_project_report_plan;
        TextView tv_project_report_progress;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_project_report_time = (TextView) itemView.findViewById(R.id.tv_project_report_time);
            tv_project_report_finish = (TextView) itemView.findViewById(R.id.tv_project_report_finish);
            tv_project_report_plan = (TextView) itemView.findViewById(R.id.tv_project_report_plan);
            tv_project_report_progress = (TextView) itemView.findViewById(R.id.tv_project_report_progress);
        }


    }


    @Override
    public ProjectReportRvAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = mLayoutInflater.from(viewGroup.getContext()).inflate(R.layout.project_report_custom_rv_item, viewGroup, false);
        return new ProjectReportRvAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ProjectReportRvAdapter.ViewHolder viewHolder, int position) {
        ProjectReportCustomRvBean item = items.get(position);

        viewHolder.tv_project_report_time.setText(item.getProject_report_time());
        viewHolder.tv_project_report_finish.setText(item.getProject_report_finish());
        viewHolder.tv_project_report_plan.setText(item.getProject_report_plan());
        viewHolder.tv_project_report_progress.setText(item.getProject_report_progress());

        //如果设置了回调则设施点击事件
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.tv_project_report_finish, pos);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(viewHolder.tv_project_report_finish, pos);
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

