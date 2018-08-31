package com.example.administrator.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class TypeInfoView extends LinearLayout {

    public TypeInfoView(Context context) {
        this(context,null);
    }

    public TypeInfoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TypeInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
