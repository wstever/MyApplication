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

import com.example.burt.android_sample_study.MainActivity;
import com.example.burt.android_sample_study.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class DemoFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdatper;
    private List<String> textList = new ArrayList<>();
    private List<Class> classList = new ArrayList<>();


    public static DemoFragment newInstance(String info) {
        Bundle args = new Bundle();
        DemoFragment fragment = new DemoFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo, null);


        // TextView tvInfo = (TextView) view.findViewById(R.id.tvInfo);
        // //tvInfo.setText(getArguments().getString("info"));
        // tvInfo.setOnClickListener(new View.OnClickListener() {
        //      @Override
        //      public void onClick(View v) {
        //          Snackbar.make(v, "hello", Snackbar.LENGTH_SHORT).show();
        //       }
        //   });


        //设置是否滑动退出
        //getActivity().setSwipeBackEnable(false);
        //initToolbarAsHome("吴小龙同學");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleerView);
        recyclerViewAdatper = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdatper);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }


    private void initData() {
        textList.add(" 1");
        textList.add("2  ");
        textList.add("3 ");
        textList.add("4 ");
        textList.add("5");
        textList.add("6");
        textList.add("7");


        classList.add(MainActivity.class);
        classList.add(MainActivity.class);
        classList.add(MainActivity.class);
        classList.add(MainActivity.class);
        classList.add(MainActivity.class);
        classList.add(MainActivity.class);
        classList.add(MainActivity.class);

        Collections.sort(textList);

        Collections.sort(classList, new Comparator<Class>() {
            public int compare(Class c1, Class c2) {
                return c1.getSimpleName().compareTo(c2.getSimpleName());
            }
        });

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