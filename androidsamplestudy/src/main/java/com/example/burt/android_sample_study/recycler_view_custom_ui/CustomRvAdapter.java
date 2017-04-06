package com.example.burt.android_sample_study.recycler_view_custom_ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.burt.android_sample_study.R;

import java.util.ArrayList;
import java.util.List;


public class CustomRvAdapter extends RecyclerView.Adapter<CustomRvAdapter.ViewHolder> {
    private List<CustomRvItem> items;
    private static final int ITEM_CONUT = 10;
    private LayoutInflater mLayoutInflater;

    OnItemClickListerner mOnItemClickListerner;




    public interface OnItemClickListerner {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    public void setOnItemClickLitener(OnItemClickListerner mOnItemClickListerner) {
        this.mOnItemClickListerner = mOnItemClickListerner;
    }


    public CustomRvAdapter() {
        super();
        items = new ArrayList<>();
        for (int i = 0; i < ITEM_CONUT; i++) {
            items.add(new CustomRvItem("CustomRvItem " + i, "This is the test CustomRvItem number " + i));
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView maintitle;
        TextView subtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            maintitle = (TextView) itemView.findViewById(R.id.maintitle);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = mLayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_rv_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        CustomRvItem item = items.get(position);
        viewHolder.maintitle.setText(item.getTitle());
        viewHolder.subtitle.setText(item.getSubtitle());


        //如果设置了回调则设施点击事件
        if (mOnItemClickListerner != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListerner.onItemClick(viewHolder.maintitle, pos);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListerner.onItemLongClick(viewHolder.maintitle, pos);
                    return false;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
