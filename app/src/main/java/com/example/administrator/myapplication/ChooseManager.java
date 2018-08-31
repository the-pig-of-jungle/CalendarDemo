package com.example.administrator.myapplication;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.haibin.calendarview.Calendar;

public class ChooseManager {
    private static ChosenItem sCurChosenItem = new ChosenItem();
    private static ChosenItem sLastChosenItem = new ChosenItem();
    private static Drawable sSelectedDrawable;
    private static Drawable sCurDayDrawable;


    public static void setCurSeletedCalendar(Calendar curSeletedCalendar, boolean isClick) {
        if (sCurChosenItem.getCalendar() != null){
            sLastChosenItem.setCalendar(sCurChosenItem.getCalendar());
            sLastChosenItem.setClick(sCurChosenItem.isClick());
        }

        sCurChosenItem.setCalendar(curSeletedCalendar);
        sCurChosenItem.setClick(isClick);
    }

    public static boolean isClick(Calendar calendar) {
        if (calendar == null) {
            return false;
        }

        return sCurChosenItem.getCalendar().equals(calendar) && sCurChosenItem.isClick();
    }

    public static Drawable getSelectedDrawable() {
        if (sSelectedDrawable == null) {
            sSelectedDrawable = ContextCompat.getDrawable(MyApplication.sContext, R.drawable.selected_day_bg);
        }
        return sSelectedDrawable;
    }

    public static Drawable getCurDayDrawable(){
        if (sCurDayDrawable == null) {
            sCurDayDrawable = ContextCompat.getDrawable(MyApplication.sContext, R.drawable.cur_day_bg);
        }
        return sCurDayDrawable;
    }

}
