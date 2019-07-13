package com.example.noplannogain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.example.noplannogain.Database.NoplanNogainDb;

import java.util.HashSet;
import java.util.List;

public class Calendar extends AppCompatActivity {

    CalendarView calendar;
    HashSet<Calendar> list = new HashSet<>();

    NoplanNogainDb npngDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        npngDB = new NoplanNogainDb(this);

        calendar = (CalendarView)findViewById(R.id.calendrier);
        List<String> workoutDay = npngDB.getWorkoutDays();
        HashSet<Calendar> convertedList = new HashSet<>();
       

    }
}
