package com.example.burt.android_sample_study.recycler_view_long;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.burt.android_sample_study.R;

import java.util.ArrayList;
import java.util.List;


public class ListViewActivity extends ListActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.long_rv_activity_listview);

        List<String> names = new ArrayList<String>();
        names.add("哈哈哈1");
        names.add("哈哈哈2");
        names.add("哈哈哈3");
        names.add("哈哈哈4");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);
    }
}
