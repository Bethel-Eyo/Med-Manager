package com.eyo.bethel.med_manager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.eyo.bethel.med_manager.data.UserDataContract.*;

public class MedicalDbHelper extends SQLiteOpenHelper{
    // The database name
    public static final String DATABASE_NAME = "medmanager.db";
    // if you change the database schema, you must increment the database version
    public static final int DATABASE_VERSION = 3;

    public MedicalDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create table to hold new medication data
        final String SQL_CREATE_MEDICATION_TABLE = "CREATE TABLE " + MedParameters.TABLE_NAME
                + " (" + MedParameters._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MedParameters.DRUG_NAME + " TEXT NOT NULL, "
                + MedParameters.DESCRIPTION + " TEXT NOT NULL, "
                + MedParameters.TABLETS_PER_INTAKE + " INTEGER NOT NULL, "
                + MedParameters.TIMES_PER_DAY + " INTEGER NOT NULL, "
                + MedParameters.START_DATE + " BLOB NOT NULL, "
                + MedParameters.END_DATE + " BLOB NOT NULL " + "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_MEDICATION_TABLE);
    }

    /* This method is called during an upgrade of the version for example
    * if you increase the database version*/
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int  newVersion) {
        Log.w(MedicalDbHelper.class.getName(),
                "Upgrading database from version" + oldVersion + " to " + newVersion +
                ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + MedParameters.TABLE_NAME);

        onCreate(database);
    }
}
