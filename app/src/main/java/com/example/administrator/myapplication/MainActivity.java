package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.coder.zzq.smartshow.toast.SmartToast;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private TextView mStartDateTxt;
    private TextView mEndDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChooseManager.setDefaultRange(2018, 9, 1, 2018, 9, 3);
        mStartDateTxt = findViewById(R.id.start_date);
        mEndDateTxt = findViewById(R.id.end_date);
        mStartDateTxt.setText("9月1日\n周六");
        mEndDateTxt.setText("9月3日\n周一");
        mRecyclerView = findViewById(R.id.calendar_list);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);


        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(new CalendarAdapter());

        List<CalendarItem> items = new ArrayList<CalendarItem>();
        items.add(null);
        items.add(null);
        items.add(null);
        items.add(null);
        items.add(null);
        items.add(null);
        ((CalendarAdapter) mRecyclerView.getAdapter()).setData(items);
        mRecyclerView.scrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1);
    }

}
