package com.example.burt.android_sample_study.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.burt.android_sample_study.R;
import com.example.burt.android_sample_study.horizontal_scrollview.HorizontalScrollViewActivity;
import com.example.burt.android_sample_study.json_demo.JsonDemoActivity;
import com.example.burt.android_sample_study.mvp_demo.UserLoginActivity;
import com.example.burt.android_sample_study.project_report.ProjectReportActivity;
import com.example.burt.android_sample_study.recycler_view.RecyclerLayoutManagerChangeActivity;
import com.example.burt.android_sample_study.recycler_view_long.SelectActivity;
import com.example.burt.android_sample_study.tab_layout.TabLayoutActivity;
import com.example.burt.android_sample_study.weekly_report.WeeklyReportActivity;

import java.util.ArrayList;
import java.util.List;


public class DemoFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdatper;
    private List<String> textList = new ArrayList<>();
    private List<Class> classList = new ArrayList<>();

    private View rootView;

    public static DemoFragment newInstance(String info) {
        Bundle args = new Bundle();
        DemoFragment fragment = new DemoFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // TextView tvInfo = (TextView) view.findViewById(R.id.tvInfo);
        // //tvInfo.setText(getArguments().getString("info"));
        // tvInfo.setOnClickListener(new View.OnClickListener() {
        //      @Override
        //      public void onClick(View v) {
        //
        //       }
        //   });


        //设置是否滑动退出
        //getActivity().setSwipeBackEnable(false);
        //initToolbarAsHome("吴小龙同學");

        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.fragment_demo, null);
            initView(rootView);// 控件初始化
        }
        return rootView;
    }

    private void initView(View rootView) {
        initData();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleerView);
        recyclerViewAdatper = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdatper);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    private void initData() {
        textList.add("RecyclerView含有Grid和Liner转换的Spinner");
        textList.add("与Java Web进行通讯");
        textList.add("长按RecyclerView的事件触发");
        textList.add("周报模块学习");
        textList.add("项目周报模块学习");
        textList.add("TabLayout学习");
        textList.add("Gallery转成了HorizonScrollview");
        textList.add("MVPDemo_以登录界面为例");


        classList.add(RecyclerLayoutManagerChangeActivity.class);
        classList.add(JsonDemoActivity.class);
        classList.add(SelectActivity.class);
        classList.add(WeeklyReportActivity.class);
        classList.add(ProjectReportActivity.class);
        classList.add(TabLayoutActivity.class);
        classList.add(HorizontalScrollViewActivity.class);
        classList.add(UserLoginActivity.class);

        //不管我的顺序什么,在这里都会被排序的~
        // Collections.sort(textList);

        //     Collections.sort(classList, new Comparator<Class>() {
        //         public int compare(Class c1, Class c2) {
        //            return c1.getSimpleName().compareTo(c2.getSimpleName());
        //        }
        //     });

    }


    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_main, parent, false);
            return new RecyclerViewAdapter.ViewHolder(view);
        }

        public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
            holder.textView.setText(textList.get(position));
        }

        public int getItemCount() {
            return textList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getActivity(), classList.get(getLayoutPosition())));
                    }
                });
            }
        }
    }
}
