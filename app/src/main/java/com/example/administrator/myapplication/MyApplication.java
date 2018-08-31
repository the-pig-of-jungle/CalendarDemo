package com.example.administrator.myapplication;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coder.zzq.smartshow.SmartShow;
import com.coder.zzq.smartshow.toast.ProcessToastCallback;

public class MyApplication extends Application {
    public static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        SmartShow.init(this);
        SmartShow.toastSetting()
                .textSizeSp(16)
                .processView(new ProcessToastCallback() {
                    @Override
                    public void processView(boolean b, View view, LinearLayout linearLayout, TextView textView) {
                        view.setBackgroundResource(R.drawable.toast_bg);
                        Drawable drawable = ContextCompat.getDrawable(MyApplication.this,R.drawable.success);
                        drawable.setBounds(0,0, (int) (drawable.getIntrinsicWidth() * 0.8f), (int) (drawable.getIntrinsicHeight() * 0.8f));
                        textView.setCompoundDrawables(null,drawable,null,null);
                    }
                });

    }
}
