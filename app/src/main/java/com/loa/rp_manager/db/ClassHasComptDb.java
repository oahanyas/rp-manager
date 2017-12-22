package com.loa.rp_manager.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by hanyas on 18/12/2017.
 */
@DatabaseTable(tableName = "job_has_comp")
public class ClassHasComptDb implements Serializable {

    public static final String ID = "id";
    @DatabaseField(columnName = ID, id = true)
    protected Integer id;

    public static final String CLASS = "job";
    @DatabaseField(foreign = true, columnName = CLASS, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1)
    protected ClassDb job;

    public static final String COMPT = "comp";
    @DatabaseField(foreign = true, columnName = COMPT, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1)
    protected ComptDb compt;

    public static final String VALUE = "value";
    @DatabaseField(columnName = VALUE)
    protected Integer value;

    public static final String BONUS = "bonus";
    @DatabaseField(columnName = BONUS)
    protected Integer bonus;

    public static final String BONUS_MAX = "bonus_max";
    @DatabaseField(columnName = BONUS_MAX)
    protected Integer bonusMax;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClassDb getJob() {
        return job;
    }

    public void setJob(ClassDb job) {
        this.job = job;
    }

    public ComptDb getCompt() {
        return compt;
    }

    public void setCompt(ComptDb compt) {
        this.compt = compt;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getBonusMax() {
        return bonusMax;
    }

    public void setBonusMax(Integer bonusMax) {
        this.bonusMax = bonusMax;
    }
}
