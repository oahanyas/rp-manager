package com.loa.rp_manager;

import android.app.Application;
import android.content.Context;
import com.crashlytics.android.Crashlytics;
import com.loa.rp_manager.db.PlayerHelper;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Hanyas on 04/09/2017.
 */
abstract class MyApplicationAbstract extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        context = this;
    }

    public static Context getInstance(){
        return context;
    }
}
