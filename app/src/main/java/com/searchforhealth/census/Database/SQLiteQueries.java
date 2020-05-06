package com.searchforhealth.census.Database;


import com.searchforhealth.census.Preference.ConstantDatabase;

public class SQLiteQueries {

    public static final String CREATE_USERNAMES_TABLE = "CREATE TABLE IF NOT EXISTS "+
            ConstantDatabase.TABLE_USERNAMES + "(" +
            ConstantDatabase.COLUMN_FIRSTNAME + " text," +
            ConstantDatabase.COLUMN_LASTNAME  + " text"+");";

}
