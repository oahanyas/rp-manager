package com.loa.rp_manager.db;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.loa.rp_manager.utils.Utils;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by hanyas on 17/09/2017.
 */
@DatabaseTable(tableName = "stats")
public class StatsDb implements Serializable {

    public static final String ID = "id";
    @DatabaseField(columnName = ID, id = true)
    protected Integer id;

    public static final String TYPE = "type";
    @DatabaseField(columnName = TYPE)
    protected String type;

    public static final String DESC = "description";
    @DatabaseField(columnName = DESC)
    protected String description;

    public void save() throws SQLException {
        OrmLiteSqliteOpenHelper helper = Utils.getHelper();

        PlayerHelper absHelper = (PlayerHelper) helper;
        @SuppressWarnings("unchecked")
        Dao<StatsDb, Integer> dao = (Dao<StatsDb, Integer>) absHelper.getDao(getClass());
        dao.createOrUpdate(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
