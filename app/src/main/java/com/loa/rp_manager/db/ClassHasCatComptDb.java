package com.loa.rp_manager.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by hanyas on 18/12/2017.
 */
@DatabaseTable(tableName = "job_has_cat_comp")
public class ClassHasCatComptDb implements Serializable {

    public static final String ID = "id";
    @DatabaseField(columnName = ID, id = true)
    protected Integer id;

    public static final String JOB = "job";
    @DatabaseField(foreign = true, columnName = JOB, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1)
    protected ClassDb classDb;

    public static final String CAT_COMPT = "cat_comp";
    @DatabaseField(foreign = true, columnName = CAT_COMPT, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1)
    protected CatComptDb catCompt;

    public static final String LIMIT = "limit";
    @DatabaseField(columnName = LIMIT)
    protected Integer limit;

    public static final String DEFAULT = "default_cout";
    @DatabaseField(columnName = DEFAULT)
    protected Integer defaultCout;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClassDb getClassDb() {
        return classDb;
    }

    public void setClassDb(ClassDb classDb) {
        this.classDb = classDb;
    }

    public CatComptDb getCatCompt() {
        return catCompt;
    }

    public void setCatCompt(CatComptDb catCompt) {
        this.catCompt = catCompt;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getDefaultCout() {
        return defaultCout;
    }

    public void setDefaultCout(Integer defaultCout) {
        this.defaultCout = defaultCout;
    }
}
