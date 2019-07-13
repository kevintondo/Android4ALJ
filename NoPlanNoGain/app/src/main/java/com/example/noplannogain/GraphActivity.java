package com.example.noplannogain;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.example.noplannogain.Model.GraphEntry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
            GraphEntry entry = (GraphEntry) entities.get(i);
            Date dateEntry = entry.getDate();
            int poidEntry = entry.getValue();
            int k=0;
        }

        List<Entry> entries = new ArrayList<Entry>();


    }
}
