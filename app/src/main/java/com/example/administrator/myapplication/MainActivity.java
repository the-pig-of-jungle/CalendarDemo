package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


import com.coder.zzq.smartshow.toast.SmartToast;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

public class MainActivity extends AppCompatActivity {
    private Toast mToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToast = Toast.makeText(this,"重置成功",Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER,0,0);
        final CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                ChooseManager.setCurSeletedCalendar(calendar,isClick);
                SmartToast.success("重置成功");
            }
        });

    }

    public void onViewClick(View view) {
        SmartToast.show("重置成功");
    }
}
