package com.loa.rp_manager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.loa.rp_manager.MyApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by anthony.succu on 19/04/2017.
 */
public class PlayerHelper extends OrmLiteSqliteOpenHelper {

    /************************************************
     * Suggested Copy/Paste code. Everything from here to the done block.
     ************************************************/
    public static final String DATABASE_PATH = MyApplication.getInstance().getFilesDir().getPath() + "/databases/";
    public static final String DATABASE_NAME = "player.db";
    private static final int DATABASE_VERSION = 1;


    public PlayerHelper(Context context) {
        super(context, DATABASE_PATH + DATABASE_NAME, null, DATABASE_VERSION);

        boolean dbexist = checkdatabase();
        if (!dbexist) {

            // If database did not exist, try copying existing database from assets folder.
            try {
                File dir = new File(DATABASE_PATH);
                dir.mkdirs();
                InputStream myinput = context.getAssets().open(DATABASE_NAME);
                String outfilename = DATABASE_PATH + DATABASE_NAME;
                Log.i(PlayerHelper.class.getName(), "DB Path : " + outfilename);
                OutputStream myoutput = new FileOutputStream(outfilename);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myinput.read(buffer)) > 0) {
                    myoutput.write(buffer, 0, length);
                }
                myoutput.flush();
                myoutput.close();
                myinput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * Check whether or not database exist
    */
    private boolean checkdatabase() {
        boolean checkdb = false;

        String myPath = DATABASE_PATH + DATABASE_NAME;
        File dbfile = new File(myPath);
        checkdb = dbfile.exists();

        Log.i(PlayerHelper.class.getName(), "DB Exist : " + checkdb);

        return checkdb;
    }

    /************************************************
     * Suggested Copy/Paste Done
     ************************************************/
    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {

    }

    /**
     * Return null if not existe or object
     *
     * @param clazz
     * @param id
     * @return
     */
    public <T> T queryForId(Class<T> clazz, int id) {
        Dao<T, Integer> accountDao;

        try {
            // Get connexion
            accountDao = getDao(clazz);
            // Get QueryBuilder
            return clazz.cast(accountDao.queryForId(id));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
