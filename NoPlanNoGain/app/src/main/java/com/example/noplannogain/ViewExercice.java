package com.example.noplannogain;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noplannogain.Database.NoplanNogainDb;
import com.example.noplannogain.Utils.Commun;

public class ViewExercice extends AppCompatActivity {

    int image_id;
    String name;

    TextView timer, title;
    ImageView detail_image;

    Button btnStart;

    boolean isRunning = false;

    NoplanNogainDb npngDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercice);

        npngDB = new NoplanNogainDb((this));

        timer = (TextView)findViewById(R.id.timer);
        title = (TextView)findViewById(R.id.titre);
        detail_image = (ImageView)findViewById(R.id.detail_image);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRunning){

                    btnStart.setText("Fin");

                    int timeLimit =0;
                    if(npngDB.getSettingMode() == 0){
                        timeLimit = Commun.TIME_LIMIT_EASY;
                    } else if(npngDB.getSettingMode() == 1){
                        timeLimit = Commun.TIME_LIMIT_MEDIUM;
                    } else if(npngDB.getSettingMode() == 2){
                        timeLimit = Commun.TIME_LIMIT_HARD;
                    }

                    new CountDownTimer(timeLimit,1000){

                        @Override
                        public void onTick(long k) {

                            timer.setText(""+k/1000);
                        }

                        @Override
                        public void onFinish() {

                            Toast.makeText(ViewExercice.this, "Terminer!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }.start();
                }
                else {
                    Toast.makeText(ViewExercice.this, "Terminer!", Toast.LENGTH_LONG).show();
                    finish();
                }

                isRunning = !isRunning;
            }
        });

        timer.setText("");

        if(getIntent() != null){
            image_id = getIntent().getIntExtra("image_id", -1);
            name = getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title.setText(name);

        }
    }
}
