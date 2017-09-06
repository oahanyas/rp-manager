package com.loa.rp_manager;

import com.loa.rp_manager.db.PlayerHelper;

/**
 * Created by Hanyas on 04/09/2017.
 */
public class MyApplication extends MyApplicationAbstract {

    @Override
    public void onCreate() {
        super.onCreate();

        new PlayerHelper(this);
    }
}
