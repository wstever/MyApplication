package com.example.burt.android_sample_study.recycler_view_custom_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.burt.android_sample_study.MainActivity;
import com.example.burt.android_sample_study.R;


public class CustomRvActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_rv_ui_activity_main);
        init();
        initEvent();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    }

    private void initEvent() {
        CustomRvAdapter mCustomRvAdapter=new CustomRvAdapter();
        recyclerView.setAdapter(mCustomRvAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//垂直线性布局
       // recyclerView.setLayoutManager(new GridLayoutManager(this,2));//线性宫格显示，类似gridview
       // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));//线性宫格显示类似瀑布流





        mCustomRvAdapter.setOnItemClickLitener(new CustomRvAdapter.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(CustomRvActivity.this, "nihao"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //mCustomRvAdapter.removeData(position);
                Toast.makeText(CustomRvActivity.this, "long_nihao"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
