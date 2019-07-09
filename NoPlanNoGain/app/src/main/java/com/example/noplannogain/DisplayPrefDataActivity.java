package com.example.noplannogain;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayPrefDataActivity extends AppCompatActivity {
    private TextView displayName;
    private TextView displaySexe;
    private TextView displayTaille;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pref_data);
        displayName = (TextView) findViewById(R.id.displayName);
        displaySexe = (TextView) findViewById(R.id.displaySexe);
        displayTaille = (TextView) findViewById(R.id.displayTaille);
        mDatabaseHelper = new DatabaseHelper(this);

        setDisplayView();
    }

    private void setDisplayView() {
        toastMessage("je suis ici !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        SQLiteOpenHelper database = new DatabaseHelper(this);
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM pref_table", null);
        if (c.moveToFirst()){
            do {
                // Passing values
                String name = c.getString(1);
                String sexe = c.getString(2);
                String taille = c.getString(3);
                // Do something Here with values
                displayName.setText(name);
                displaySexe.setText(sexe);
                displayTaille.setText(taille);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public void setPersonnalsDatas(View v){
        Intent intent = new Intent(DisplayPrefDataActivity.this, DatabaseLightActivity.class);
        startActivity(intent);
    }
}
