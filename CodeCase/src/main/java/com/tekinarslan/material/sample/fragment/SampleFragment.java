package com.tekinarslan.material.sample.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;


import com.tekinarslan.material.sample.util.DateUtil;
import com.tekinarslan.material.sample.bean.HourAndMinute;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.custom.FloatingActionButton;
import com.tekinarslan.material.sample.custom.ProgressBarCircular;

import java.util.Calendar;
import java.util.Date;

public class SampleFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_POSITION = "position";

    private int position;


    private Button mBtnFromTime;

    private Button mBtnToTime;

    private Button mBtnDate;

    public static SampleFragment newInstance(int position) {
        SampleFragment f = new SampleFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.page, container, false);

        ProgressBarCircular progressBarCircular = (ProgressBarCircular) rootView.findViewById(R.id.progress);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabButton);
        fab.setDrawableIcon(getResources().getDrawable(R.drawable.plus));
        switch (position) {
            case 0:
                fab.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                break;
            case 1:
                fab.setBackgroundColor(getResources().getColor(R.color.red));
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.red));

                break;
            case 2:
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.blue));
                fab.setBackgroundColor(getResources().getColor(R.color.blue));

                break;
            case 3:
                fab.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));

                break;
        }


        mBtnFromTime = (Button) rootView.findViewById(R.id.from_time);
        mBtnToTime = (Button) rootView.findViewById(R.id.to_time);

        mBtnDate = (Button) rootView.findViewById(R.id.date);
        mBtnDate.setText(DateUtil.dateToString(new Date()));
        mBtnDate.setOnClickListener(this);
        mBtnFromTime.setOnClickListener(this);
        mBtnToTime.setOnClickListener(this);


        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.from_time:
                showTimePicker(mBtnFromTime, mBtnFromTime.getText().toString());
                break;
            case R.id.to_time:
                showTimePicker(mBtnToTime, mBtnToTime.getText().toString());
                break;
            case R.id.date:
                showDatePicker(mBtnDate, mBtnDate.getText().toString());
                break;

        }
    }

    private void showTimePicker(final Button text, String str) {
        int hourOfDay = 00;
        int minute = 00;

        if (!TextUtils.isEmpty(str)) {
            HourAndMinute ham = new HourAndMinute(str);
            hourOfDay = ham.getHour();
            minute = ham.getMinute();
        }

        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                HourAndMinute ham = new HourAndMinute();
                ham.setHour(hourOfDay);
                ham.setMinute(minute);
                text.setText(ham.toString(true));
            }
        };
        TimePickerDialog mTimePickerDialog = TimePickerDialog.newInstance(callback, hourOfDay, minute, true, R.style.DateTimePicker);
        mTimePickerDialog.setCancelable(true);
        mTimePickerDialog.show(getActivity().getSupportFragmentManager(), "mTimePickerDialog");
    }

    private void showDatePicker(final Button text, String str) {
        int year = 0;
        int monthOfYear = 0;
        int dayOfMonth = 0;

        if (!TextUtils.isEmpty(str)) {
            Calendar c = Calendar.getInstance();
            c.setTime(DateUtil.parseDate(str));
            year = c.get(Calendar.YEAR);
            monthOfYear = c.get(Calendar.MONTH);
            dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        }

        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Date d = c.getTime();
                text.setText(DateUtil.dateToString(d));
            }
        };

        DatePickerDialog mDatePickerDialog = DatePickerDialog.newInstance(callback, year, monthOfYear, dayOfMonth, R.style.DateTimePicker);
        mDatePickerDialog.setCancelable(true);
        mDatePickerDialog.show(getActivity().getSupportFragmentManager(), "DatePickerDialog");
    }
}