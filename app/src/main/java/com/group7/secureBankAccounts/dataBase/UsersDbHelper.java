package com.group7.secureBankAccounts.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class UsersDbHelper  extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BankAccount.db";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Variable.TABLE_NAME + " (" +
                    Variable._ID + " INTEGER PRIMARY KEY," +
                    Variable.COLUMN_NAME_FIRST_NAME + " TEXT," +
                    Variable.COLUMN_NAME_LAST_NAME + " TEXT," +
                    Variable.COLUMN_NAME_PASSWORD + " TEXT)";





    public UsersDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
