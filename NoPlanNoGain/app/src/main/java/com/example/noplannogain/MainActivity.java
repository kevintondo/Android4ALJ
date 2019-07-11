package com.example.noplannogain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String USER_PREF = "user_pref";
    private final String EXO_NAME_FIELD_NAME = "exercice_name";
    private final String EXO_DESC_FIELD_NAME = "exercice_description";
    private final String EXO_TYPE_FIELD_NAME = "exercice_type";



    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(USER_PREF, MODE_PRIVATE);



    }

    public void writeExercice(View v) {

        String exoNameInput = "test_name";
        String exoDescInput = "test_description";
        String exoTypeInput = "test_type";

        sharedPreferences.edit().putString(EXO_NAME_FIELD_NAME, exoNameInput).apply();
        sharedPreferences.edit().putString(EXO_DESC_FIELD_NAME, exoDescInput).apply();
        sharedPreferences.edit().putString(EXO_TYPE_FIELD_NAME, exoTypeInput).apply();
    }

    public void readExercice(View v) {
        String exerciceName = sharedPreferences.getString(EXO_NAME_FIELD_NAME, "Nothing");
        String exerciceDesc = sharedPreferences.getString(EXO_DESC_FIELD_NAME, "Nothing");
        String exerciceType = sharedPreferences.getString(EXO_TYPE_FIELD_NAME, "Nothing");

        // Toast.makeText(this, val, Toast.LENGTH_SHORT).show();
    }

    public void setPersonnalsDatas(View v){
        Intent intent = new Intent(MainActivity.this, DatabaseLightActivity.class);
        startActivity(intent);
    }

    public void setGotoHome(View v){

        Intent intent2 = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent2);

    }
}
