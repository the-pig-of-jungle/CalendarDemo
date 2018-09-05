package com.example.administrator.myapplication;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.support.v4.content.ContextCompat;

import com.haibin.calendarview.Calendar;

import java.util.Date;

public class ChooseManager {
    private static ChosenItem sCurChosenItem = new ChosenItem();
    private static ChosenItem sLastChosenItem = new ChosenItem();
    private static Drawable sSelectedDrawable;
    private static Drawable sCurDayDrawable;


    public static void setCurSeletedCalendar(Calendar curSeletedCalendar, boolean isClick) {
        if (!isClick){
            return;
        }
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


    public static Drawable getRangeSelectedDrawable(Calendar calendar) {
        if (sLastChosenItem.getCalendar().equals(sCurChosenItem.getCalendar())){
            return getSelectedDrawable();
        }else if (!calendar.equals(sLastChosenItem.getCalendar()) && !calendar.equals(sCurChosenItem.getCalendar())){
            return getRangeDrawable();
        }else if (calendar.equals(sLastChosenItem.getCalendar())){
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
            return last.before(cur) ? getBeforeEdgeDrawable() : getAfterEdgeDrawable();
        }else {
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
            return last.after(cur) ? getBeforeEdgeDrawable() : getAfterEdgeDrawable();
        }
    }

    private static Drawable getAfterEdgeDrawable() {
        if (sAfterEdgeDrawable == null){
            sAfterEdgeDrawable = ContextCompat.getDrawable(MyApplication.sContext,R.drawable.after_selected);
        }

        return sAfterEdgeDrawable;
    }

    private static Drawable getBeforeEdgeDrawable() {
        if (sBeforeEdgeDrawable == null){
            sBeforeEdgeDrawable = ContextCompat.getDrawable(MyApplication.sContext,R.drawable.before_selected);
        }

        return sBeforeEdgeDrawable;
    }

    private static Drawable sRangeDrawable;
    private static Drawable sBeforeEdgeDrawable;
    private static Drawable sAfterEdgeDrawable;

    private static Drawable getRangeDrawable() {
        if (sRangeDrawable == null){
            sRangeDrawable = ContextCompat.getDrawable(MyApplication.sContext,R.drawable.range_selected_bg);
        }
        return sRangeDrawable;
    }


    public static void setDefaultRange(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDar) {
        sLastChosenItem.setCalendar(Calendar.get()
                .year(startYear)
                .month(startMonth)
                .day(startDay));
        sLastChosenItem.setClick(true);
        sCurChosenItem.setCalendar(Calendar.get()
        .year(endYear)
        .month(endMonth)
        .day(endDar));
;       sCurChosenItem.setClick(true);
    }
}
