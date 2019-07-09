package com.example.noplannogain;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_light);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSexe = (EditText) findViewById(R.id.editTextSexe);
        editTextTaille = (EditText) findViewById(R.id.editTextTaille);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String sexe = editTextSexe.getText().toString();
                String taille = editTextTaille.getText().toString();
                if (editTextName.length() != 0 && editTextSexe.length() != 0 && editTextTaille.length() != 0 ) {
                    AddData(name, sexe, taille);
                    editTextName.setText("");
                    editTextSexe.setText("");
                    editTextTaille.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });
    }

    public void seeData(View v){
        Intent intent = new Intent(DatabaseLightActivity.this, DatabaseLightActivity.class);
        startActivity(intent);
    }

    public void AddData(String name, String sexe, String taille) {
        boolean insertData = mDatabaseHelper.addData(name, sexe, taille);

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
}
