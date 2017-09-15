package com.loa.rp_manager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by anthony.succu on 19/04/2017.
 */
public class PlayerHelper extends OrmLiteSqliteOpenHelper {

    /************************************************
     * Suggested Copy/Paste code. Everything from here to the done block.
     ************************************************/
    private static final String DATABASE_NAME = "player.db";
    private static final int DATABASE_VERSION = 1;

    public PlayerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /************************************************
     * Suggested Copy/Paste Done
     ************************************************/
    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.dropTable(connectionSource, PlayerDb.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {
            TableUtils.dropTable(connectionSource, PlayerDb.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
