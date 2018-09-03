package com.example.administrator.myapplication;

import android.app.Application;
import android.content.Context;

import com.coder.zzq.smartshow.SmartShow;
import com.coder.zzq.smartshow.snackbar.custom.Snackbar;
import com.coder.zzq.smartshow.toast.IToastSetting;
import com.coder.zzq.smartshow.toast.SmartToast;

public class MyApplication extends Application {
    public static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        SmartShow.init(this);
        SmartShow.toastSetting()
                .actionWhenDuplicate(IToastSetting.ACTION_REPEAT);
    }
}
