package com.loa.rp_manager;

import com.loa.rp_manager.db.PlayerHelper;
import com.loa.rp_manager.utils.Utils;

/**
 * Created by Hanyas on 04/09/2017.
 */
public class MyApplication extends MyApplicationAbstract {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.setHelper(new PlayerHelper(this));
    }
}
