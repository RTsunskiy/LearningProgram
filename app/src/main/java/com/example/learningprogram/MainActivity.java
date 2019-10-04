package com.example.learningprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Spinner;

import com.example.learningprogram.models.LearningProgramProvider;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LearningProgramProvider mLearningProvider = new LearningProgramProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        initSpinner();

    }

    private void initSpinner() {
        Spinner spinner = findViewById(R.id.lectors_spinner);
        List<String> lectors = mLearningProvider.provideLectors();
        Collections.sort(lectors);
        lectors.add(0, getResources().getString(R.string.all));
        LectorSpinnerAdapter adapter = new LectorSpinnerAdapter(lectors);
        spinner.setAdapter(adapter);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.learning_program_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        LearningProgramAdapter adapter = new LearningProgramAdapter();
        adapter.setmLectures(mLearningProvider.provideLecture());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
    }
}
