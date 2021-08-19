package com.example.mincoffee;

import android.app.Application;

import com.example.mincoffee.di.component.AppComponent;
import com.google.gson.Gson;

public class MyApplication extends Application {
    private static MyApplication mSelf;
    private Gson mGson;
    private AppComponent mAppComponent;

    public static MyApplication self() {
        return mSelf;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSelf = this;
        mGson = new Gson();
        mAppComponent = new AppComponent();
    }

    public Gson getGson() {
        return mGson;
    }
    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
