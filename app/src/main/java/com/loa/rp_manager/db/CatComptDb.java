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
public class CatComptDb implements Serializable {

    public static final String ID = "id";
    @DatabaseField(columnName = ID, id = true)
    protected Integer id;

    public static final String TITRE = "titre";
    @DatabaseField(columnName = TITRE)
    protected String titre;

    public static final String PRINCIPAL = "principal";
    @DatabaseField(columnName = PRINCIPAL)
    protected Boolean principal;

    public static final String COMPTS = "COMPTS";
    @ForeignCollectionField(columnName = COMPTS, eager = true)
    protected ForeignCollection<ComptDb> competences;

    public void save() throws SQLException {
        OrmLiteSqliteOpenHelper helper = Utils.getHelper();

        PlayerHelper absHelper = (PlayerHelper) helper;
        @SuppressWarnings("unchecked")
        Dao<CatComptDb, Integer> dao = (Dao<CatComptDb, Integer>) absHelper.getDao(getClass());
        dao.createOrUpdate(this);
    }

    public ForeignCollection<ComptDb> getListCompt() {
        return competences;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public ForeignCollection<ComptDb> getCompetences() {
        return competences;
    }

    public void setCompetences(ForeignCollection<ComptDb> competences) {
        this.competences = competences;
    }
}
