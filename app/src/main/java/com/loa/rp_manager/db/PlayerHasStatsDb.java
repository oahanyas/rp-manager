package com.loa.rp_manager.db;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.loa.rp_manager.utils.Utils;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by Hanyas on 06/09/2017.
 */
@DatabaseTable(tableName = "player_has_stats")
public class PlayerHasStatsDb implements Serializable {

    @DatabaseField(foreign = true, columnName = "player")
    protected PlayerDb playerDb;

    @DatabaseField(foreign = true, columnName = "stats")
    protected StatsDb statsDb;

    public static final String VALUE = "value";
    @DatabaseField(columnName = VALUE)
    protected Integer value;

    public void save() throws SQLException {
        OrmLiteSqliteOpenHelper helper = Utils.getHelper();

        PlayerHelper absHelper = (PlayerHelper) helper;
        @SuppressWarnings("unchecked")
        Dao<PlayerHasStatsDb, Integer> dao = (Dao<PlayerHasStatsDb, Integer>) absHelper.getDao(getClass());
        dao.createOrUpdate(this);
    }

    public PlayerDb getPlayerDb() {
        return playerDb;
    }

    public void setPlayerDb(PlayerDb playerDb) {
        this.playerDb = playerDb;
    }

    public StatsDb getStatsDb() {
        return statsDb;
    }

    public void setStatsDb(StatsDb statsDb) {
        this.statsDb = statsDb;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
