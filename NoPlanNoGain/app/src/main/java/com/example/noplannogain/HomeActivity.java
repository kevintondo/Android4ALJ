package com.example.noplannogain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    Button btnExercice, btnSetting, btnCalendrier;
    ImageView btnTraining;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);

        btnExercice = (Button)findViewById(R.id.btnExercices);
        btnSetting = (Button)findViewById(R.id.btnSettings);
        btnTraining = (ImageView)findViewById(R.id.btnTraining);

        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Daily_Training.class);
                startActivity(intent);
            }
        });


        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingPage.class);
                startActivity(intent);
            }
        });

        btnExercice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ListExercices.class);
                startActivity(intent);
            }
        });

    }
}
