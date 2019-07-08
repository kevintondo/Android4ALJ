package com.example.noplannogain;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class ExerciceWidget extends AppWidgetProvider {

    private static final String TAG = "Ajout d'exercices";

    public static void updateWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId/*, String text*/) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.addexercicewidget);  // layout du widget
        views.setTextViewText(R.id.w_difficulty_label, "pouet");

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int appWidgetId : appWidgetIds) {
            updateWidget(context, appWidgetManager, appWidgetId/*, new Date().toString()*/);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}
