package com.example.noplannogain;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class WidgetConfActivity extends AppCompatActivity {

    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_conf);
        // default fail
        setResult(RESULT_CANCELED);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(null != extras) {
            appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

       new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWidget();
            }
        };
    }

    private void addWidget() {
        // update widget
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        ExerciceWidget.updateWidget(this, appWidgetManager, appWidgetId);

        // All good
        Intent resultIntent = new Intent();
        resultIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultIntent);

        finish();
    }
}
