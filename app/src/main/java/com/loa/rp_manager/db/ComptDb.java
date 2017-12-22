package com.loa.rp_manager.db;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.loa.rp_manager.utils.Utils;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by Hanyas on 25/09/2017.
 */
@DatabaseTable(tableName = "comp")
public class ComptDb implements Serializable {

    public static final String ID = "id";
    @DatabaseField(columnName = ID, id = true)
    protected Integer id;

    public static final String CATCOMPT = "cat_comp";
    @DatabaseField(foreign = true, columnName = CATCOMPT, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1)
    protected CatComptDb catCompt;

    public static final String TITRE = "titre";
    @DatabaseField(columnName = TITRE)
    protected String titre;

    public static final String DESC = "description";
    @DatabaseField(columnName = DESC)
    protected String description;

    public void save() throws SQLException {
        OrmLiteSqliteOpenHelper helper = Utils.getHelper();

        PlayerHelper absHelper = (PlayerHelper) helper;
        @SuppressWarnings("unchecked")
        Dao<ComptDb, Integer> dao = (Dao<ComptDb, Integer>) absHelper.getDao(getClass());
        dao.createOrUpdate(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CatComptDb getCatCompt() {
        return catCompt;
    }

    public void setCatCompt(CatComptDb catCompt) {
        this.catCompt = catCompt;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
