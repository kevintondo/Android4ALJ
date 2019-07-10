package com.example.noplannogain;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "pref_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "sexe";
    private static final String COL4 = "taille";
    private static final String COL5 = "poid";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " +
                TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +COL2 +" TEXT," +COL3+" TEXT,"+COL4+" TEXT,"+COL5+" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i > i1) {
            db.execSQL("ALTER TABLE "+TABLE_NAME+" ADD COLUMN poid TEXT");
        }
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, String sexe, String taille, String poid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, sexe);
        contentValues.put(COL4, taille);
        contentValues.put(COL5, poid);

        Log.d(TAG, "addData: Adding " + name +" "+ sexe +" "+ taille + " to " + TABLE_NAME);
        db.execSQL("DELETE FROM "+TABLE_NAME+";");
        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String> getDatas(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> result = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT * FROM pref_table", null);
        if (c.moveToFirst()){
            do {
                // Passing values

                result.add(c.getString(1));
                result.add(c.getString(2));
                result.add(c.getString(3));
                result.add(c.getString(4));
                // Do something Here with values
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return result;
    }

}
