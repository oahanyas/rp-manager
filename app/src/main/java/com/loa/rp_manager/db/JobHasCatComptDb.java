package com.loa.rp_manager.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by hanyas on 18/12/2017.
 */
@DatabaseTable(tableName = "player")
public class JobHasCatComptDb implements Serializable {

    public static final String ID = "id";
    @DatabaseField(columnName = ID, id = true)
    protected Integer id;

    public static final String CLASS = "CLASS";
    @DatabaseField(foreign = true, columnName = "CLASS", foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1)
    protected Class job;

    public static final String CAT_COMPT = "CAT_COMPT";
    @DatabaseField(foreign = true, columnName = "CAT_COMPT", foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1)
    protected CatComptDb cat_compt;

    public static final String LIMIT = "LIMIT";
    @DatabaseField(columnName = LIMIT)
    protected Integer limit;

    public static final String DEFAULT = "DEFAULT";
    @DatabaseField(columnName = DEFAULT)
    protected Integer default_cout;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Class getJob() {
        return job;
    }

    public void setJob(Class job) {
        this.job = job;
    }

    public CatComptDb getCat_compt() {
        return cat_compt;
    }

    public void setCat_compt(CatComptDb cat_compt) {
        this.cat_compt = cat_compt;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getDefault_cout() {
        return default_cout;
    }

    public void setDefault_cout(Integer default_cout) {
        this.default_cout = default_cout;
    }
}
