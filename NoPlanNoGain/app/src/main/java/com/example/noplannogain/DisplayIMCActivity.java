package com.example.noplannogain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;

public class DisplayIMCActivity extends AppCompatActivity {
    private TextView showImc;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        showImc = (TextView) findViewById(R.id.showImc);

        mDatabaseHelper = new DatabaseHelper(this);

        try {
            setDisplayView();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setDisplayView() throws ParseException {
        String message = "";
        ArrayList<String> arrayImc = mDatabaseHelper.getDatas();
        String nom = arrayImc.get(0);
        String sexe = arrayImc.get(1);
        String taille = arrayImc.get(2);
        String poid = arrayImc.get(3);
        double imc = (Integer.parseInt(poid) / Math.pow(Integer.parseInt(taille) / 10, 2.0))*100;

        if(imc < 18.5){
            message = nom+", vous être en stade de maigreur";
        }
        else if(imc < 25){
            message = nom+", vous être en stade normal";
        }
        else{
            message = nom+", vous être en stade de surpoid";
        }

        message += " ("+imc+")";

        showImc.setText(message);

    }

    public void Return(View v){
        Intent intent = new Intent(DisplayIMCActivity.this, DisplayPrefDataActivity.class);
        startActivity(intent);
    }
}
