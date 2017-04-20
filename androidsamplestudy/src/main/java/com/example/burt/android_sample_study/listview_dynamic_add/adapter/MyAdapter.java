package com.example.burt.android_sample_study.listview_dynamic_add.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.burt.android_sample_study.R;
import com.example.burt.android_sample_study.listview_dynamic_add.bean.BaseItem;
import com.example.burt.android_sample_study.listview_dynamic_add.bean.ItemBean1;
import com.example.burt.android_sample_study.listview_dynamic_add.bean.ItemBean2;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context mContext = null;//上下文
    private LayoutInflater mInflater = null;

    private List<BaseItem> mData = null;//要显示的数据

    public MyAdapter(Context context, List<BaseItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    //添加一个新的Item，并通知listview进行显示刷新
    public void addItem(BaseItem newItem) {
        this.mData.add(newItem);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        return mData.get(position).getItem_type();
    }

    @Override
    public int getViewTypeCount() {
        return ItemType.ITEM_TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return this.mData.size();
    }

    @Override
    public Object getItem(int i) {

        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View viewItem1 = null;
        View viewItem2 = null;

        int itemType = this.getItemViewType(position);
        if (itemType == ViewHolder1.ITEM_VIEW_TYPE_1) {
            //第一种item
            ViewHolder1 viewHolder1 = null;
            if (convertView == null) {
                //没有缓存过
                viewHolder1 = new ViewHolder1();
                viewItem1 = this.mInflater.inflate(R.layout.list_view_dynamic_item_1, null, false);
                viewHolder1.textView = (TextView) viewItem1.findViewById(R.id.
                        lv_tv_1);
                viewHolder1.imageView = (ImageView) viewItem1.findViewById(R.id.
                        lv_iv_1);
                viewItem1.setTag(viewHolder1);
                convertView = viewItem1;
            } else {
                viewHolder1 = (ViewHolder1) convertView.getTag();
            }
            viewHolder1.textView.setText(((ItemBean1) mData.get(position)).getName());
            viewHolder1.imageView.setBackgroundResource(R.drawable.btn_light);
        } else if (itemType == ViewHolder2.ITEM_VIEW_TYPE_2) {
            //第二种item
            ViewHolder2 viewHolder2 = null;
            if (convertView == null) {
                //没有缓存过
                viewHolder2 = new ViewHolder2();
                viewItem2 = this.mInflater.inflate(R.layout.list_view_dynamic_item_2, null, false);
                viewHolder2.textView1 = (TextView) viewItem2.findViewById(R.id.
                        lv_tv_2);
                viewHolder2.textView2 = (TextView) viewItem2.findViewById(R.id.
                        lv_tv_3);
                viewItem2.setTag(viewHolder2);
                convertView = viewItem2;
            } else {
                viewHolder2 = (ViewHolder2) convertView.getTag();
            }
            viewHolder2.textView1.setText(((ItemBean2) mData.get(position)).getName());
            viewHolder2.textView2.setText(((ItemBean2) mData.get(position)).getAddress());
        }

        return convertView;
    }
}
