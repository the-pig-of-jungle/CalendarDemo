package com.example.administrator.myapplication;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarUtil;
import com.haibin.calendarview.CalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder>
implements CalendarView.OnCalendarSelectListener{
    private List<CalendarItem> mData = new ArrayList<>();
    private int mDefaultPos;
    private java.util.Calendar mCalendar = java.util.Calendar.getInstance();
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy年MM月");

    public CalendarAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CalendarLayout calendarLayout = (CalendarLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(calendarLayout);
        mDefaultPos = viewHolder.mMonthViewPager.getCurrentItem();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mCalendarView.setOnCalendarSelectListener(this);
        holder.mMonthViewPager.setCurrentItem(mDefaultPos - (getItemCount() - position - 1));
        mCalendar.set(holder.mCalendarView.getCurYear(),holder.mCalendarView.getCurMonth() - 1,
                holder.mCalendarView.getCurDay(),0,0,0);
        mCalendar.add(java.util.Calendar.MONTH,-(getItemCount() - position - 1));
        Date date = mCalendar.getTime();
        holder.mMonthLabel.setText(mSimpleDateFormat.format(date));

//            holder.mCalendarView.setRange(2014,1,1,
//                    holder.mCalendarView.getCurYear(),holder.mCalendarView.getCurMonth(),
//                    holder.mCalendarView.getCurDay() + 1);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        if (!isClick){
            return;
        }

            notifyDataSetChanged();


        if (ChooseManager.needDrawRangeSelected() && ChooseManager.isDrawed()){
            ChooseManager.resetDrawRange();
        }
        ChooseManager.setCurSeletedCalendar(calendar,isClick);

            notifyDataSetChanged();


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewPager mMonthViewPager;
        TextView  mMonthLabel;
        CalendarView mCalendarView;
        public ViewHolder(View itemView) {
            super(itemView);
            mCalendarView= itemView.findViewById(R.id.calendarView);
            mMonthViewPager = mCalendarView.getMonthViewPager();
            mMonthLabel = itemView.findViewById(R.id.month_label);
        }
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.mMonthViewPager.postInvalidate();
    }

    public void setData(List<CalendarItem> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
