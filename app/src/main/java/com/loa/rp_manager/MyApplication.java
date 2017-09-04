package com.loa.rp_manager;

import android.app.Application;
import android.content.Context;

/**
 * Created by anthony.succu on 04/09/2017.
 */
public class MyApplication extends Application {
    private static Context context;

    public MyApplication() {
        context = getApplicationContext();
    }

    static Context getContext(){
        return context;
    }
}
