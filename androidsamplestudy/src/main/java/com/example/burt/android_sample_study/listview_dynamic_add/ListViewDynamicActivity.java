package com.example.burt.android_sample_study.listview_dynamic_add;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.burt.android_sample_study.R;
import com.example.burt.android_sample_study.listview_dynamic_add.adapter.MyAdapter;
import com.example.burt.android_sample_study.listview_dynamic_add.adapter.ViewHolder1;
import com.example.burt.android_sample_study.listview_dynamic_add.adapter.ViewHolder2;
import com.example.burt.android_sample_study.listview_dynamic_add.bean.BaseItem;
import com.example.burt.android_sample_study.listview_dynamic_add.bean.ItemBean1;
import com.example.burt.android_sample_study.listview_dynamic_add.bean.ItemBean2;

import java.util.ArrayList;
import java.util.List;


public class ListViewDynamicActivity extends Activity {
    private ListView listView = null;
    //适配器
    private MyAdapter myAdapter = null;
    //数据
    private List<BaseItem> mData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_dynamic);
        findViewsById();
        init();
        addListeners();
    }

    private void findViewsById() {
        this.listView = (ListView) findViewById(R.id.main_activity_listview);
    }

    private void init() {
        this.mData = new ArrayList<BaseItem>();
        this.mData.add(new ItemBean1(ViewHolder1.ITEM_VIEW_TYPE_1, "name1", "iamgePath1"));
        this.mData.add(new ItemBean1(ViewHolder1.ITEM_VIEW_TYPE_1, "name2", "iamgePath2"));
        this.mData.add(new ItemBean2(ViewHolder2.ITEM_VIEW_TYPE_2, "name1", "address1"));
        this.mData.add(new ItemBean2(ViewHolder2.ITEM_VIEW_TYPE_2, "name1", "address1"));
        this.mData.add(new ItemBean2(ViewHolder2.ITEM_VIEW_TYPE_2, "name1", "address1"));
        this.mData.add(new ItemBean1(ViewHolder1.ITEM_VIEW_TYPE_1, "name2", "iamgePath2"));
        this.mData.add(new ItemBean2(ViewHolder2.ITEM_VIEW_TYPE_2, "name1", "address1"));
        this.mData.add(new ItemBean1(ViewHolder1.ITEM_VIEW_TYPE_1, "name2", "iamgePath2"));
        this.mData.add(new ItemBean1(ViewHolder1.ITEM_VIEW_TYPE_1, "name2", "iamgePath2"));
        this.mData.add(new ItemBean2(ViewHolder2.ITEM_VIEW_TYPE_2, "name1", "address1"));

        this.myAdapter = new MyAdapter(this, this.mData);
        this.listView.setAdapter(this.myAdapter);
    }

    private void addListeners() {

    }

}
