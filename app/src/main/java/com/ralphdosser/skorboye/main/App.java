package com.ralphdosser.skorboye.main;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

public class App extends MultiDexApplication {

    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        // TODO - firebase analytics

        // TODO - crashlytics
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }
}
