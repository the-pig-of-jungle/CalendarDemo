package com.example.administrator.myapplication;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.haibin.calendarview.Calendar;

import java.util.Date;

public class ChooseManager {
    private static ChosenItem sCurChosenItem = new ChosenItem();
    private static ChosenItem sLastChosenItem = new ChosenItem();
    private static Drawable sSelectedDrawable;
    private static Drawable sCurDayDrawable;


    public static void setCurSeletedCalendar(Calendar curSeletedCalendar, boolean isClick) {
        if (sCurChosenItem.getCalendar() != null) {
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

        return sCurChosenItem.getCalendar() != null && sCurChosenItem.getCalendar().equals(calendar) && sCurChosenItem.isClick();
    }

    public static Drawable getSelectedDrawable() {
        if (sSelectedDrawable == null) {
            sSelectedDrawable = ContextCompat.getDrawable(MyApplication.sContext, R.drawable.selected_day_bg);
        }
        return sSelectedDrawable;
    }

    public static Drawable getCurDayDrawable() {
        if (sCurDayDrawable == null) {
            sCurDayDrawable = ContextCompat.getDrawable(MyApplication.sContext, R.drawable.cur_day_bg);
        }
        return sCurDayDrawable;
    }

    public static boolean needDrawRangeSelected() {
        return sLastChosenItem.getCalendar() != null && sLastChosenItem.isClick()
                && sCurChosenItem.getCalendar() != null && sCurChosenItem.isClick();
    }


    public static void resetDrawRange() {
        sLastChosenItem.setCalendar(null);
        sLastChosenItem.setClick(false);
        sCurChosenItem.setCalendar(null);
        sCurChosenItem.setClick(false);
        sHasDraw = false;
    }

    public static Calendar getCurSelected() {
        return sCurChosenItem.getCalendar();
    }

    private static boolean sHasDraw;

    public static void hasDraw(boolean b) {
        sHasDraw = b;
    }

    public static boolean isDrawed() {
        return sHasDraw;
    }

    private static java.util.Calendar sCalendar = java.util.Calendar.getInstance();

    public static boolean isCellNeedDraw(Calendar calendar) {
        sCalendar.set(sCurChosenItem.getCalendar().getYear(),
                sCurChosenItem.getCalendar().getMonth() - 1,
                sCurChosenItem.getCalendar().getDay(),
                0,0,0);
        Date cur = sCalendar.getTime();

        sCalendar.set(sLastChosenItem.getCalendar().getYear(),
                sLastChosenItem.getCalendar().getMonth() - 1,
                sLastChosenItem.getCalendar().getDay(),
                0,0,0);
        Date last = sCalendar.getTime();

        sCalendar.set(calendar.getYear(),
                calendar.getMonth() - 1,
                calendar.getDay(),
                0,0,0);
        Date cell = sCalendar.getTime();

        Date min = last.before(cur) ? last : cur;
        Date max = last.after(cur) ? last : cur;


        return (cell.before(max) && cell.after(min)) || (cell.equals(cur) || cell.equals(last));

    }



}
