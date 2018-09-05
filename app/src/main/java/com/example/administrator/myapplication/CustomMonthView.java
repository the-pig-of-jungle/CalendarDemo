package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarUtil;
import com.haibin.calendarview.MonthView;

import java.util.Date;

public class CustomMonthView extends MonthView {

    protected Paint mTextPaint = new Paint();
    protected Paint mSchemeBasicPaint = new Paint();
    protected Paint mCurDayBgPaint = new Paint();
    protected float mRadio;
    protected int mPadding;
    protected float mSchemeBaseLine;

    public CustomMonthView(Context context) {
        super(context);

        mTextPaint.setTextSize(CalendarUtil.dipToPx(context, 8));
        mTextPaint.setColor(0xffFFFFFF);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setFakeBoldText(true);

        mSchemeBasicPaint.setAntiAlias(true);
        mSchemeBasicPaint.setStyle(Paint.Style.FILL);
        mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
        mSchemeBasicPaint.setColor(0xffed5353);
        mSchemeBasicPaint.setFakeBoldText(true);
        mRadio = CalendarUtil.dipToPx(getContext(), 7);
        mPadding = CalendarUtil.dipToPx(getContext(), 4);
        Paint.FontMetrics metrics = mSchemeBasicPaint.getFontMetrics();
        mSchemeBaseLine = mRadio - metrics.descent + (metrics.bottom - metrics.top) / 2 + CalendarUtil.dipToPx(getContext(), 1);

        mCurDayBgPaint.setAntiAlias(true);
        mCurDayBgPaint.setStyle(Paint.Style.FILL);
        mCurDayBgPaint.setColor(Color.parseColor("#f6f6f6"));
    }


    /**
     * @param canvas    canvas
     * @param calendar  日历日历calendar
     * @param x         日历Card x起点坐标
     * @param y         日历Card y起点坐标
     * @param hasScheme hasScheme 非标记的日期
     * @return true 则绘制onDrawScheme，因为这里背景色不是是互斥的
     */

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        Drawable d = ChooseManager.getSelectedDrawable();
        mSelectedPaint.setStyle(Paint.Style.FILL);

        if (calendar.isCurrentDay() && !ChooseManager.isClick(calendar)){
            d = ChooseManager.getCurDayDrawable();
        }

        if (!ChooseManager.isClick(calendar) && !calendar.isCurrentDay()){
            return true;
        }

        d.setBounds(x + mPadding, y + mPadding, x + mItemWidth - mPadding, y + mItemHeight - mPadding);
        d.draw(canvas);
        return true;
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        mSchemeBasicPaint.setColor(calendar.getSchemeColor());

        canvas.drawCircle(x + mItemWidth - mPadding - mRadio / 2, y + mPadding + mRadio, mRadio, mSchemeBasicPaint);

        canvas.drawText(calendar.getScheme(),
                x + mItemWidth - mPadding - mRadio / 2 - getTextWidth(calendar.getScheme()) / 2,
                y + mPadding + mSchemeBaseLine, mTextPaint);
    }


    /**
     * 获取字体的宽
     *
     * @param text text
     * @return return
     */
    protected float getTextWidth(String text) {
        return mTextPaint.measureText(text);
    }


    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {

        if (calendar.isCurrentDay() && !isSelected && isRealCurDay(calendar)) {
            Drawable d = ChooseManager.getCurDayDrawable();
            d.setBounds(x + mPadding, y + mPadding, x + mItemWidth - mPadding, y + mItemHeight - mPadding);
            d.draw(canvas);
        }

        if (ChooseManager.needDrawRangeSelected()){
            if (ChooseManager.isCellNeedDraw(calendar)) {

                Drawable d = ChooseManager.getRangeSelectedDrawable(calendar);
                d.setBounds(x, y, x + mItemWidth, y + mItemHeight - mPadding);
                d.draw(canvas);
            }
            ChooseManager.hasDraw(true);
        }



        int cx = x + mItemWidth / 2;
        int top = y - mItemHeight / 6;

        if (isSelected) {
            if (ChooseManager.isClick(calendar)) {
                canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                        mSelectTextPaint);
                canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight / 10, mSelectedLunarTextPaint);
            } else {
                canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                        mCurMonthTextPaint);
                canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight / 10, mCurMonthLunarTextPaint);
            }
        } else if (hasScheme) {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mSchemeTextPaint : mOtherMonthTextPaint);

            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight / 10,
                    calendar.isCurrentDay() ? mCurDayLunarTextPaint : mSchemeLunarTextPaint);
        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mCurMonthTextPaint : mOtherMonthTextPaint);
            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight / 10,
                    calendar.isCurrentDay() ? mCurDayLunarTextPaint :
                            calendar.isCurrentMonth() ? mCurMonthLunarTextPaint : mOtherMonthLunarTextPaint);
        }


        if (ChooseManager.needDrawRangeSelected()){
            if (ChooseManager.isCellNeedDraw(calendar)) {
                canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,mSelectTextPaint);
                canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight / 10,mSelectedLunarTextPaint);
            }

        }

    }

    private Date mDate = new Date();
    private boolean isRealCurDay(Calendar calendar) {
       return CalendarUtil.getDate("yyyy",mDate) == calendar.getYear()
               && CalendarUtil.getDate("MM",mDate) == calendar.getMonth()
               && CalendarUtil.getDate("dd",mDate) == calendar.getDay();
    }

}
