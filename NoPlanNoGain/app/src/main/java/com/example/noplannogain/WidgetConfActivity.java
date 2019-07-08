package com.example.noplannogain;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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

        //EditText editName = findViewById(R.id.w_nameInput);

        Button btnAddExercice = findViewById(R.id.w_btn_plus);
        btnAddExercice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExercice();
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWidget();
            }
        });
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

    public void saveExercice() {
        LinearLayout linearLayout = findViewById(R.id.w_linear_layout);

        String val = "toto";
        Toast.makeText(this, val, Toast.LENGTH_SHORT).show();
    }
}
