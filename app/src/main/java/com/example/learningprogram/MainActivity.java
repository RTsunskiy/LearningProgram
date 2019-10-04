package com.example.learningprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.learningprogram.models.LearningProgramProvider;
import com.example.learningprogram.models.Lecture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LearningProgramProvider mLearningProvider = new LearningProgramProvider();

    List<Lecture> list = new ArrayList<>();

    Spinner spinner;

    List<Lecture> list3 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        initSpinner();

    }

    private void initSpinner() {
        spinner = findViewById(R.id.lectors_spinner);
        List<String> lectors = mLearningProvider.provideLectors();
        LectorSpinnerAdapter adapter = new LectorSpinnerAdapter(lectors);
        Collections.sort(lectors);
        lectors.add(0, getResources().getString(R.string.all));
        spinner.setAdapter(adapter);
    }

    private void initRecyclerView() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {

                List<String> list = mLearningProvider.provideLectors();

                List<Lecture> list2 = mLearningProvider.provideLecture();

                String str = list.get(selectedItemPosition);
                for (int i = 0; i < list2.size(); i++) {
                    if (list2.get(i).getmLector().equals(str)) {
                        list3.add(list2.get(i));
                    }
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        RecyclerView recyclerView = findViewById(R.id.learning_program_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        LearningProgramAdapter adapter = new LearningProgramAdapter();
        adapter.setmLectures(list3);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);



    }


}
