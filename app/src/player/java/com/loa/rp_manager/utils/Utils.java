package com.loa.rp_manager.utils;

import com.loa.rp_manager.db.PlayerHelper;

/**
 * Created by Hanyas on 06/09/2017.
 */
public class Utils extends UtilsAbsract {
    private static PlayerHelper helper;

    public static void setHelper(PlayerHelper helper) {
        Utils.helper = helper;
    }

    public static PlayerHelper getHelper() {
        return helper;
    }
}
