package com.example.noplannogain;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.noplannogain.Database.NoplanNogainDb;

import java.util.Calendar;
import java.util.Date;

public class SettingPage extends AppCompatActivity {

    Button btnSave;
    RadioButton radioEasy, radioMedium, radioHard;
    RadioGroup radioGroup;
    NoplanNogainDb npngDB;
    ToggleButton switchAlarm;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //Init view
        btnSave = (Button)findViewById(R.id.btnSave);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioEasy = (RadioButton)findViewById(R.id.radioEasy);
        radioMedium = (RadioButton)findViewById(R.id.radioMedium);
        radioHard = (RadioButton)findViewById(R.id.radioHard);

        switchAlarm = (ToggleButton)findViewById(R.id.switchAlarm);

        timePicker = (TimePicker)findViewById(R.id.timePicker);

        npngDB = new NoplanNogainDb(this);

        int mode= npngDB.getSettingMode();
        setRadioButton(mode);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWorkout();
                saveAlarm(switchAlarm.isChecked());
                Toast.makeText(SettingPage.this, "Saved!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void saveAlarm(boolean checked){
        if(checked){
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;

            intent = new Intent(SettingPage.this,AlarmNotificationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0,intent,0);


            //Heure
            Calendar calendar = Calendar.getInstance();
            Date toDay = Calendar.getInstance().getTime();
            calendar.set(toDay.getYear(), toDay.getMonth(), toDay.getDay(), timePicker.getHour(), timePicker.getMinute());

            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


            Log.d("DEBUG", "Alarm sonnera a " + timePicker.getHour() + ":" + timePicker.getMinute());


        }

        else {
            Intent intent = new Intent(SettingPage.this,AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,intent,0);

            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

            manager.cancel(pendingIntent);


        }
    }

    private void saveWorkout(){
        int selectedID = radioGroup.getCheckedRadioButtonId();

        if(selectedID == radioEasy.getId()){
            npngDB.saveSettingMode(0);
        } else if(selectedID == radioMedium.getId()){
            npngDB.saveSettingMode(1);
        } else if(selectedID == radioHard.getId()){
            npngDB.saveSettingMode(2);
        }
    }

    private void setRadioButton(int mode){
        if(mode == 0){
            radioGroup.check(R.id.radioEasy);
        } else if(mode == 1){
            radioGroup.check(R.id.radioMedium);
        } else if (mode == 2){
            radioGroup.check((R.id.radioHard));
        }
    }
}
