package com.example.administrator.myapplication;

import com.haibin.calendarview.Calendar;

public class ChosenItem {

    private Calendar mCalendar;
    private boolean mIsClick;

    public ChosenItem(Calendar calendar, boolean isClick) {
        mCalendar = calendar;
        mIsClick = isClick;
    }

    public ChosenItem() {
    }

    public Calendar getCalendar() {
        return mCalendar;
    }

    public void setCalendar(Calendar calendar) {
        mCalendar = calendar;
    }

    public boolean isClick() {
        return mIsClick;
    }

    public void setClick(boolean click) {
        mIsClick = click;
    }

}
