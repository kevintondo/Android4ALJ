package com.example.noplannogain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.noplannogain.Adapter.RecyclerViewAdapter;
import com.example.noplannogain.Model.Exercice;

import java.util.ArrayList;
import java.util.List;

public class ListExercices extends AppCompatActivity {

    List<Exercice> exerciceList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercices);

        initData();

        recyclerView = (RecyclerView)findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(exerciceList, getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        exerciceList.add(new Exercice(R.drawable.men1, "Single Biceps Curl"));
        exerciceList.add(new Exercice(R.drawable.men2, "Biceps"));
        exerciceList.add(new Exercice(R.drawable.men3, "Pompes"));
        exerciceList.add(new Exercice(R.drawable.men4, "Latéral Biceps Curl"));
        exerciceList.add(new Exercice(R.drawable.men5, "Haussement d’épaules"));
        exerciceList.add(new Exercice(R.drawable.men6, "Fente avec poid"));
        exerciceList.add(new Exercice(R.drawable.men7, "Tapis de course"));
        exerciceList.add(new Exercice(R.drawable.men8, "Crunches"));

    }
}
