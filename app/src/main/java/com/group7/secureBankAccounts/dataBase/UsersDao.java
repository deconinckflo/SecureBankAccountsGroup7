package com.group7.secureBankAccounts.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.group7.secureBankAccounts.ApiRequest.Api;
import com.group7.secureBankAccounts.ApiRequest.ApiInterface;
import com.group7.secureBankAccounts.data.model.Users;

import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersDao {

    public Users getUserById(Context c, Integer id) {
        UsersDbHelper dbHelper = new UsersDbHelper(c);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor dbCursor = db.query(Variable.TABLE_NAME,
                new String[]{Variable.COLUMN_NAME_FIRST_NAME, Variable.COLUMN_NAME_LAST_NAME, Variable.COLUMN_NAME_PASSWORD},
                Variable._ID + "= ?",
                new String[]{id.toString()}, null, null, null);

        String firstName;
        String lastName;
        String password;
        if (!dbCursor.moveToFirst()) {
            return null;
        } else {
            firstName = dbCursor.getString(dbCursor.getColumnIndex(Variable.COLUMN_NAME_FIRST_NAME));
            lastName = dbCursor.getString(dbCursor.getColumnIndex(Variable.COLUMN_NAME_FIRST_NAME));
            password = dbCursor.getString(dbCursor.getColumnIndex(Variable.COLUMN_NAME_PASSWORD));
        }

        return new Users(firstName, lastName, id, password);


    }

    public void insertUsers(Context c, String firstName, String lastName, String hash) {
        UsersDbHelper dbHelper = new UsersDbHelper(c);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlQuery = "INSERT INTO " + Variable.TABLE_NAME + "(" + Variable.COLUMN_NAME_FIRST_NAME + "," + Variable.COLUMN_NAME_LAST_NAME + "," + Variable.COLUMN_NAME_PASSWORD + ") VALUES(?,?,?)";
        SQLiteStatement statement = db.compileStatement(sqlQuery);
        statement.bindString(1, firstName);
        statement.bindString(2, lastName);
        statement.bindString(3, hash);
        statement.executeInsert();
    }


    public Users getUserByFirstNameAndLastName(Context c, String firstName, String lastName) {
        UsersDbHelper dbHelper = new UsersDbHelper(c);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor dbCursor = db.query(Variable.TABLE_NAME,
                new String[]{Variable._ID, Variable.COLUMN_NAME_PASSWORD},
                Variable.COLUMN_NAME_FIRST_NAME + "= ? AND " + Variable.COLUMN_NAME_LAST_NAME + "= ?",
                new String[]{firstName, lastName}, null, null, null);


        if (!dbCursor.moveToFirst()) {
            return null;
        } else {
            String password = dbCursor.getString(dbCursor.getColumnIndex(Variable.COLUMN_NAME_PASSWORD));
            Integer id = dbCursor.getInt(dbCursor.getColumnIndex(Variable._ID));
            return new Users(firstName, lastName, id, password);
        }
    }
}
