package com.example.noplannogain.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class NoplanNogainDb extends SQLiteAssetHelper {

    private  static final String DB_NAME = "noplannogain.db";
    private static final int DB_VER=1;

    public NoplanNogainDb(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public int getSettingMode(){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"Mode"};
        String sqlTable = "Setting";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("Mode"));

    }


    public void saveSettingMode(int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Setting SET Mode = "+value;
        db.execSQL(query);
    }


    public List<String> getWorkoutDays(){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"Days"};
        String sqlTable = "WorkoutDays";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);


        List<String> result = new ArrayList<>();
        if(c.moveToFirst()){

            do{
                result.add(c.getString(c.getColumnIndex("Days")));
            }while(c.moveToNext());
        }
        return result;

    }


    public void saveDay(String value){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO WorkoutDays(Days) VALUES('%s');", value);
        db.execSQL(query);
    }



}
