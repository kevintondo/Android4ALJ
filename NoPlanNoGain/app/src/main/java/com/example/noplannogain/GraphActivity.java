package com.example.noplannogain;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.example.noplannogain.Model.GraphEntry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GraphActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener {
    LineChart lineChart;
    GraphEntry graphEntry;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_layout);

        lineChart = (LineChart)findViewById(R.id.linechart);

        lineChart.setOnChartGestureListener(GraphActivity.this);
        lineChart.setOnChartValueSelectedListener(GraphActivity.this);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.setDrawGridBackground(true);

        ArrayList<Entry> entries = new ArrayList<Entry>();
        ArrayList<Entry> pI = new ArrayList<Entry>();
//
//
//        ArrayList<Entry> x = new ArrayList<Entry>();
//        ArrayList<Entry> y = new ArrayList<Entry>();
//
//        LineDataSet lineDataSet1 = new LineDataSet(x, "TIME");

        DatabaseHelper dh = new DatabaseHelper(this);
        ArrayList entities =  dh.getGraphDatas();
        int poidIdeal = dh.getBestWeight();

        for(int i=0; i<entities.size();i++){
            GraphEntry graphEntry = (GraphEntry) entities.get(i);
            Date dateEntry = graphEntry.getDate();
            int poidEntry = graphEntry.getValue();

            Entry entry = new Entry(i , poidEntry);
            Entry entry2 = new Entry(i , poidIdeal);
            Log.d("tttttttttttttttttttest", Integer.toString(poidIdeal));
            entries.add(entry);
            pI.add(entry2);
        }


        LineDataSet set1;
        LineDataSet set2;

        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(entries);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
            set2 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set2.setValues(pI);
        } else {
            set1 = new LineDataSet(entries, "Poid");
            set1.setDrawIcons(false);
//            set1.enableDashedLine(10f, 5f, 0f);
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.DKGRAY);
            set1.setCircleColor(Color.DKGRAY);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            set2 = new LineDataSet(pI, "Poid Ideal");
            set2.setDrawIcons(false);
//            set2.enableDashedLine(10f, 5f, 0f);
//            set2.enableDashedHighlightLine(10f, 5f, 0f);
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.RED);
            set2.setLineWidth(1f);
            set2.setCircleRadius(3f);
            set2.setDrawCircleHole(false);
            set2.setValueTextSize(9f);
            set2.setDrawFilled(true);
            set2.setFormLineWidth(1f);
            set2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set2.setFormSize(15.f);
            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue);
                set2.setFillDrawable(drawable);
            } else {
                set2.setFillColor(Color.RED);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            dataSets.add(set2);
            LineData data = new LineData(dataSets);
            lineChart.setData(data);
        }
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
