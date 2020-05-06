package com.searchforhealth.census.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.searchforhealth.census.Preference.ConstantDatabase;



public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context){

        super(context, ConstantDatabase.DATABASE_NAME, null, ConstantDatabase.DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteQueries.CREATE_USERNAMES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
