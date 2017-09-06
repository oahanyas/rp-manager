package com.loa.rp_manager.db;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.loa.rp_manager.utils.Utils;

import java.sql.SQLException;

/**
 * Created by Hanyas on 06/09/2017.
 */
@DatabaseTable(tableName = "player")
public class PlayerDb {

    public static final String ID = "id";
    @DatabaseField(columnName = ID, id = true)
    protected Integer id;

    public static final String NAME = "NAME";
    @DatabaseField(columnName = NAME, canBeNull = false)
    protected String name;

    public static final String RACE = "RACE";
    @DatabaseField(columnName = RACE, canBeNull = false)
    protected String race;

    public static final String ORIGIN = "ORIGIN";
    @DatabaseField(columnName = ORIGIN)
    protected String origin;

    public static final String SEXE = "SEXE";
    @DatabaseField(columnName = SEXE)
    protected Boolean sexe;

    public static final String SIZE = "SIZE";
    @DatabaseField(columnName = SIZE)
    protected Integer size;

    public static final String WEIGHT = "WEIGHT";
    @DatabaseField(columnName = WEIGHT)
    protected Integer weight;

    public void save() throws SQLException {
        OrmLiteSqliteOpenHelper helper = Utils.getHelper();

        PlayerHelper absHelper = (PlayerHelper) helper;
        @SuppressWarnings("unchecked")
        Dao<PlayerDb, String> dao = absHelper.getDao(getClass());
        dao.createOrUpdate(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Boolean getSexe() {
        return sexe;
    }

    public void setSexe(Boolean sexe) {
        this.sexe = sexe;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
