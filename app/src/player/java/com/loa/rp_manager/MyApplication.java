package com.loa.rp_manager;

import com.loa.rp_manager.db.PlayerHelper;
import com.loa.rp_manager.utils.Utils;

import java.io.File;

import static com.loa.rp_manager.db.PlayerHelper.DATABASE_NAME;
import static com.loa.rp_manager.db.PlayerHelper.DATABASE_PATH;

/**
 * Created by Hanyas on 04/09/2017.
 */
public class MyApplication extends MyApplicationAbstract {

    @Override
    public void onCreate() {
        super.onCreate();

        /*
        String myPath = DATABASE_PATH + DATABASE_NAME;
        File dbfile = new File(myPath);
        dbfile.delete();
        /**/

        Utils.setHelper(new PlayerHelper(this));
    }
}
