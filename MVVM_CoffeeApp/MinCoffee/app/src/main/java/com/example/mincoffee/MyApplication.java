package com.example.mincoffee;

import android.app.Application;

import com.google.gson.Gson;

public class CustomApplication extends Application {
    private static CustomApplication mSelf;
    private Gson mGson;
    private AppContainer mAppContainer;

    public static CustomApplication self() {
        return mSelf;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSelf = this;
        mGson = new Gson();
        mAppContainer = new AppContainer();
    }

    public Gson getGson() {
        return mGson;
    }
    public AppContainer getAppContainer(){
        return mAppContainer;
    }
}
