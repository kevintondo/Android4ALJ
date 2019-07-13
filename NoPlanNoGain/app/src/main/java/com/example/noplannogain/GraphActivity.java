package com.example.noplannogain;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.example.noplannogain.Model.GraphEntry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {
    LineChart lineChart;
    GraphEntry graphEntry;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_layout);

        lineChart = (LineChart)findViewById(R.id.linechart);

        DatabaseHelper dh = new DatabaseHelper(this);
        ArrayList entities =  dh.getGraphDatas();

        for(int i=0; i<entities.size();i++){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            try {
                Date date = (Date) format.parse((String)entities.get(i));
                GraphEntry graphEntry = new GraphEntry(date,(int)entities.get(i));
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        List<Entry> entries = new ArrayList<Entry>();


    }
}
