package com.customerglu.customerglu;

import android.app.Application;
import android.content.Context;

public class CustomerGluApp extends Application {

    public static Context mContext;
    public static CustomerGluApp activity;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getmContext() {
        return mContext;
    }

    public static CustomerGluApp getActivity() {

        return activity;
    }


}