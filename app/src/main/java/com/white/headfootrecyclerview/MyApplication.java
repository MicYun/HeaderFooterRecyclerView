package com.white.headfootrecyclerview;

import android.app.Application;

/**
 * Created by MicYun on 2018/4/12.
 */

public class MyApplication extends Application {
    public static MyApplication application;

    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }
}
