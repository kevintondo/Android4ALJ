package com.example.noplannogain;


import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;

import com.example.noplannogain.Model.GraphEntry;

import java.util.Date;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "pref_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "sexe";
    private static final String COL4 = "taille";
    private static final String COL5 = "poid";

    private static final String TABLE_GRAPH_NAME = "graph_table";
    private static final String COLGRAPH1 = "ID";
    private static final String COLGRAPH2 = "poid";
    private static final String COLGRAPH3 = "date";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 9);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +COL2 +" TEXT," +COL3+" TEXT,"+COL4+" INTEGER,"+COL5+" INTEGER)";
        db.execSQL(createTable);

        String createGraphTable = "CREATE TABLE IF NOT EXISTS " +
                TABLE_GRAPH_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +COLGRAPH2 +" INTEGER," +COLGRAPH3+" DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(createGraphTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i > i1) {

        }
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }

    @TargetApi(Build.VERSION_CODES.N)
    public boolean addData(String name, String sexe, String taille, String poid) {

            java.sql.Date dateC = new java.sql.Date(System.currentTimeMillis());
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:m:s");
            String formattedDate = df.format(dateC);


        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValueInfos = new ContentValues();
        contentValueInfos.put(COL2, name);
        contentValueInfos.put(COL3, sexe);
        contentValueInfos.put(COL4, taille);
        contentValueInfos.put(COL5, poid);

        ContentValues contentValueGraph = new ContentValues();
//        contentValueGraph.put(COLGRAPH3, String.valueOf(formattedDate));
        contentValueGraph.put(COLGRAPH2, Integer.parseInt(poid));

        db.execSQL("DELETE FROM "+TABLE_NAME+";");
        long result = db.insert(TABLE_NAME, null, contentValueInfos);

        long resultGraph = db.insert(TABLE_GRAPH_NAME, null, contentValueGraph);

        //if date as inserted incorrectly it will return -1
        if (result == -1 || resultGraph == -1) {
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

    @TargetApi(Build.VERSION_CODES.N)
    public ArrayList<GraphEntry> getGraphDatas(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<GraphEntry> result = new ArrayList<GraphEntry>();
        Cursor cursor = db.rawQuery("SELECT * FROM graph_table", null);
        if (cursor.moveToFirst()){
            do {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                int poid = cursor.getInt(cursor.getColumnIndex("poid"));
                try {
                    Date date1=new SimpleDateFormat("yyyy-MM-dd H:mm:ss").parse(date);
                    GraphEntry graphEntry = new GraphEntry(date1, poid);
                    result.add(graphEntry);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return result;
    }

    public int getBestWeight(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM pref_table", null);
        int poidIdeal = 0;
        if (c.moveToFirst()){
            do {
                int taille = c.getInt(3);
                Log.d("test",Integer.toString(taille));
                 poidIdeal = (taille - 100);
            } while(c.moveToNext());
        }
        c.close();
        db.close();

        return poidIdeal;


    }

    private Date stringToDate(String aDate) {

        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            simpledateformat = new SimpleDateFormat("EEE MMM d HH:mm:ss zz yyyy");
        }
        Date stringDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            stringDate = (Date) simpledateformat.parse(aDate, pos);
        }
        return stringDate;

    }



}
