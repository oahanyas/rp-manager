package com.loa.rp_manager.db;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.loa.rp_manager.utils.Utils;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by Hanyas on 06/09/2017.
 */
@DatabaseTable(tableName = "player")
public class PlayerDb implements Serializable {

    public static final String ID = "id";
    @DatabaseField(columnName = ID, generatedId = true)
    protected Integer id;

    public static final String JOB = "job";
    @DatabaseField(foreign = true, columnName = JOB, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1)
    protected ClassDb classDb;

    public static final String NAME = "name";
    @DatabaseField(columnName = NAME, canBeNull = false)
    protected String name;

    public static final String EXP = "exp";
    @DatabaseField(columnName = EXP, canBeNull = false)
    protected Integer exp = 0;

    public static final String LVL = "lvl";
    @DatabaseField(columnName = LVL, canBeNull = false)
    protected Integer lvl = 1;

    public static final String RACE = "race";
    @DatabaseField(columnName = RACE)
    protected String race = "humain";

    public static final String ORIGIN = "origin";
    @DatabaseField(columnName = ORIGIN)
    protected String origin;

    public static final String SEXE = "sexe";
    @DatabaseField(columnName = SEXE)
    protected Boolean sexe;

    public static final String SIZE = "size";
    @DatabaseField(columnName = SIZE)
    protected Integer size = 0;

    public static final String WEIGHT = "weight";
    @DatabaseField(columnName = WEIGHT)
    protected Integer weight = 0;

    public static final String BEAUTY = "beauty";
    @DatabaseField(columnName = BEAUTY)
    protected Integer beauty = 0;

    @ForeignCollectionField()
    protected ForeignCollection<PlayerHasStatsDb> playerHasStatsDb;

    public void save() throws SQLException {
        OrmLiteSqliteOpenHelper helper = Utils.getHelper();

        PlayerHelper absHelper = (PlayerHelper) helper;
        @SuppressWarnings("unchecked")
        Dao<PlayerDb, Integer> dao = (Dao<PlayerDb, Integer>) absHelper.getDao(getClass());
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

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
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

    public ForeignCollection<PlayerHasStatsDb> getPlayerHasStatsDb() {
        return playerHasStatsDb;
    }

    public void setPlayerHasStatsDb(ForeignCollection<PlayerHasStatsDb> playerHasStatsDb) {
        this.playerHasStatsDb = playerHasStatsDb;
    }

    public Integer getBeauty() {
        return beauty;
    }

    public void setBeauty(Integer beauty) {
        this.beauty = beauty;
    }

    public ClassDb getClassDb() {
        return classDb;
    }

    public void setClassDb(ClassDb classDb) {
        this.classDb = classDb;
    }
}
