package com.loa.rp_manager.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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
}
