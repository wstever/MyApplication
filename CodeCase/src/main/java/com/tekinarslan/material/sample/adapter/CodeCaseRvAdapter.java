package com.tekinarslan.material.sample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.CodeCaseBean;

import java.util.List;


public class CodeCaseRvAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<CodeCaseBean> mDataSet = null;
    private CodeCaseRvAdapter.OnItemClickListener mListener;
    private CodeCaseRvAdapter.OnItemLongClickListener mLongListener;



    public CodeCaseRvAdapter(List<CodeCaseBean> dataSet) {
        this.mDataSet = dataSet;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_code_case, viewGroup, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onItemClick(v, (String) itemView.getTag());
            }
        });



        return new MainAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder viewHolder, int i) {
        String data = mDataSet.get(i).getName();
        viewHolder.bindData(data);
        viewHolder.itemView.setTag(data);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public void removeItem(int pos){
        mDataSet.remove(pos);
        notifyItemRemoved(pos);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_platform);
        }

        public void bindData(String s) {
            if (s != null)
                tv.setText(s);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, String data);
    }

    public void setOnItemClickListener(CodeCaseRvAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }


    public interface OnItemLongClickListener {
        public void onItemLongClick(View view, String data);
    }

    public void setOnItemLongClickListener(CodeCaseRvAdapter.OnItemLongClickListener listener) {
        this.mLongListener = listener;
    }
}
