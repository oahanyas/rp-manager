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
public class ClassDb implements Serializable {

    public static final String ID = "id";
    @DatabaseField(columnName = ID, id = true)
    protected Integer id;

    public static final String TITLRE = "titre";
    @DatabaseField(columnName = TITLRE)
    protected String titre;

    public static final String DESC = "description";
    @DatabaseField(columnName = DESC)
    protected String description;

    public static final String ACHETYPE = "achetype";
    @DatabaseField(columnName = ACHETYPE)
    protected String achetype;

    public static final String MULTI_PV = "multiplicateur_pv";
    @DatabaseField(columnName = MULTI_PV)
    protected int multiplicateuPv;

    public static final String PV = "pv";
    @DatabaseField(columnName = PV)
    protected int pv;

    public static final String INIT = "initiative";
    @DatabaseField(columnName = INIT)
    protected int initiative;

    public static final String RUNEINNE = "rune_inne";
    @DatabaseField(columnName = RUNEINNE)
    protected int runeInne;

    public void save() throws SQLException {
        OrmLiteSqliteOpenHelper helper = Utils.getHelper();

        PlayerHelper absHelper = (PlayerHelper) helper;
        @SuppressWarnings("unchecked")
        Dao<ClassDb, Integer> dao = (Dao<ClassDb, Integer>) absHelper.getDao(getClass());
        dao.createOrUpdate(this);
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAchetype() {
        return achetype;
    }

    public void setAchetype(String achetype) {
        this.achetype = achetype;
    }

    public int getMultiplicateuPv() {
        return multiplicateuPv;
    }

    public void setMultiplicateuPv(int multiplicateuPv) {
        this.multiplicateuPv = multiplicateuPv;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getRuneInne() {
        return runeInne;
    }

    public void setRuneInne(int runeInne) {
        this.runeInne = runeInne;
    }
}
