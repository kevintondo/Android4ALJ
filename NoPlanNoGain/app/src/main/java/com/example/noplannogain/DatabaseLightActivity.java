package com.example.noplannogain;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatabaseLightActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseLightActivity";

    DatabaseHelper mDatabaseHelper;
    private EditText editTextName;
    private EditText editTextSexe;
    private EditText editTextTaille;
    private EditText editTextPoid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_light);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSexe = (EditText) findViewById(R.id.editTextSexe);
        editTextTaille = (EditText) findViewById(R.id.editTextTaille);
        editTextPoid = (EditText) findViewById(R.id.editTextPoid);

        //if data isset, display

        SQLiteOpenHelper database = new DatabaseHelper(this);
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM pref_table", null);
        if (c.moveToFirst()){
            do {
                // Passing values
                String name = c.getString(1);
                String sexe = c.getString(2);
                String taille = c.getString(3);
                String poid = c.getString(4);

                // Do something Here with values
                editTextName.setText(name);
                editTextSexe.setText(sexe);
                editTextTaille.setText(taille);
                editTextPoid.setText(poid);
            } while(c.moveToNext());
        }
        c.close();
        db.close();


        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String sexe = editTextSexe.getText().toString();
                String taille = editTextTaille.getText().toString();
                String poid = editTextPoid.getText().toString();
                if (editTextName.length() != 0 && editTextSexe.length() != 0 && editTextTaille.length() != 0 && editTextPoid.length() != 0 ) {
                    AddData(name, sexe, taille, poid);
                    editTextName.setText("");
                    editTextSexe.setText("");
                    editTextTaille.setText("");
                    editTextPoid.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });
    }

    public void AddData(String name, String sexe, String taille, String poid) {
        boolean insertData = mDatabaseHelper.addData(name, sexe, taille, poid);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public void displayPersonnalsDatas(View v){
        Intent intent = new Intent(DatabaseLightActivity.this, DisplayPrefDataActivity.class);
        startActivity(intent);
    }

    public void displayMenu(View v){
        Intent intent = new Intent(DatabaseLightActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
