package com.example.noplannogain;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.noplannogain.Database.NoplanNogainDb;
import com.example.noplannogain.Model.Exercice;
import com.example.noplannogain.Utils.Commun;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Daily_Training extends AppCompatActivity {

    Button btnStart;
    ImageView ex_img;
    TextView txtGetReady, txtCountDown, txtTimer, ex_name;
    ProgressBar progressBar;
    LinearLayout layoutGetReady;

    int ex_id=0, limit_time=0;

    List<Exercice> list = new ArrayList<>();

    NoplanNogainDb npngDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__training);

        initData();

        npngDB = new NoplanNogainDb(this);



        btnStart = (Button)findViewById(R.id.btnStart);

        ex_img = (ImageView)findViewById(R.id.detail_image);

        txtCountDown = (TextView)findViewById(R.id.textCountDown);
        txtGetReady = (TextView)findViewById(R.id.textGetReady);
        txtTimer = (TextView)findViewById(R.id.timer);
        ex_name = (TextView)findViewById(R.id.titre);

        layoutGetReady = (LinearLayout)findViewById(R.id.layout_get_ready);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        //Set data
        progressBar.setMax(list.size());

        setExerciseInformation(ex_id);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnStart.getText().toString().toLowerCase().equals("start")){
                    showGetReady();
                    btnStart.setText("Done");
                } else if(btnStart.getText().toString().toLowerCase().equals("done")){


                    if(npngDB.getSettingMode() ==0){
                        exercicesEasyCountDown.cancel();

                    } else if(npngDB.getSettingMode() ==1){
                        exercicesMediumCountDown.cancel();

                    } else if(npngDB.getSettingMode() ==2){
                        exercicesHardCountDown.cancel();

                    }
                    restCountDown.cancel();

                    if(ex_id<list.size()){
                        showRestTime();
                        ex_id++;
                        progressBar.setProgress(ex_id);
                        txtTimer.setText("");
                    }else {
                        showFinished();
                    }
                }  else {

                    if(npngDB.getSettingMode() ==0){
                        exercicesEasyCountDown.cancel();

                    } else if(npngDB.getSettingMode() ==1){
                        exercicesMediumCountDown.cancel();

                    } else if(npngDB.getSettingMode() ==2){
                        exercicesHardCountDown.cancel();

                    }
                    restCountDown.cancel();

                    if(ex_id < list.size()){
                        setExerciseInformation(ex_id);
                    } else {
                        showFinished();
                    }
                }
            }
        });

    }

    private void showRestTime() {
        ex_img.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        btnStart.setText("Skip");

        btnStart.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);

        restCountDown.start();

        txtGetReady.setText("Temps de repos");

    }


    private void showGetReady(){
        ex_img.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("Get Ready");

        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long l) {

                txtCountDown.setText(""+(l-1000)/1000);
            }

            @Override
            public void onFinish() {

                showExercices();
            }
        }.start();
    }

    private void showExercices() {

        if(ex_id<list.size()){
            ex_img.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetReady.setVisibility(View.INVISIBLE);


            if(npngDB.getSettingMode() ==0){
                exercicesEasyCountDown.start();

            } else if(npngDB.getSettingMode() ==1){
                exercicesMediumCountDown.start();

            } else if(npngDB.getSettingMode() ==2){
                exercicesHardCountDown.start();

            }

            //Data
            ex_img.setImageResource(list.get(ex_id).getImage_id());
            ex_name.setText(list.get(ex_id).getName());



        } else {
            showFinished();
        }
    }

    private void showFinished() {
        ex_img.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("Terminer !");
        txtCountDown.setText("Felicitation ! \n La session du jour est terminée");
        txtCountDown.setTextSize(20);

        //Sauvegarder session finie dans BD
        npngDB.saveDay("" + Calendar.getInstance().getTimeInMillis());
    }


    //CountDown

    CountDownTimer exercicesEasyCountDown = new CountDownTimer(Commun.TIME_LIMIT_EASY, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {

            if(ex_id<list.size() - 1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            } else {
                showFinished();
            }
        }
    };

    CountDownTimer exercicesMediumCountDown = new CountDownTimer(Commun.TIME_LIMIT_EASY, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {

            if(ex_id<list.size() - 1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            } else {
                showFinished();
            }
        }
    };

    CountDownTimer exercicesHardCountDown = new CountDownTimer(Commun.TIME_LIMIT_EASY, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {

            if(ex_id<list.size() - 1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            } else {
                showFinished();
            }
        }
    };

    CountDownTimer restCountDown = new CountDownTimer(2000, 1000) {
        @Override
        public void onTick(long l) {
            txtCountDown.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {

            setExerciseInformation(ex_id);
            showExercices();
        }
    };


    private void setExerciseInformation(int id) {

        ex_img.setImageResource(list.get(id).getImage_id());
        ex_name.setText(list.get(id).getName());
        btnStart.setText("Start");

        ex_img.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.INVISIBLE);
    }


    private void initData(){
        list.add(new Exercice(R.drawable.men1, "Single Biceps Curl"));
        list.add(new Exercice(R.drawable.men2, "Biceps"));
        list.add(new Exercice(R.drawable.men3, "Pompes"));
//        list.add(new Exercice(R.drawable.men4, "Latéral Biceps Curl"));
//        list.add(new Exercice(R.drawable.men5, "Haussement d’épaules"));
//        list.add(new Exercice(R.drawable.men6, "Fente avec poid"));
//        list.add(new Exercice(R.drawable.men7, "Tapis de course"));
//        list.add(new Exercice(R.drawable.men8, "Crunches"));

    }
}
