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
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningprogram.models.LearningProgramProvider;
import com.example.learningprogram.models.Lecture;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private LearningProgramProvider learningProgramProvider = new LearningProgramProvider();
    private static final int POSITION_ALL = 0;
    private RecyclerView recyclerView;
    private Spinner lectorsSpinner;
    private Spinner weeksSpinner;
    private TextView weekTextView;

    private int getKeyByValue(Map<Integer, String> map, String value) {
        int res = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key).equals(value)) {
                res = key;
                break;
            }
        }
        return res;
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.learning_program_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        LearningProgramAdapter adapter = new LearningProgramAdapter();
        adapter.setLectureList(learningProgramProvider.provideLecture());
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView(List<Lecture> lectureList) {
        LearningProgramAdapter adapter = new LearningProgramAdapter();
        adapter.setLectureList(lectureList);
        recyclerView.setAdapter(adapter);
    }

    private void initSpinnerLectors() {
        lectorsSpinner = findViewById(R.id.lectors_spinner);
        List<String> lectors = learningProgramProvider.provideLectors();
        Collections.sort(lectors);
        lectors.add(POSITION_ALL, getResources().getString(R.string.all));
        LectorSpinnerAdapter adapter = new LectorSpinnerAdapter(lectors);
        lectorsSpinner.setAdapter(adapter);

        lectorsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                weekTextView.setVisibility(View.GONE);
                String selectedLector = adapterView.getSelectedItem().toString();

                if (selectedLector.equals(getResources().getString(R.string.all))) {
                    initRecyclerView(learningProgramProvider.provideLecture());
                    return;
                }

                List<Lecture> listWithSelectedLector = new ArrayList<>();
                for (Lecture lecture : learningProgramProvider.provideLecture()) {
                    if (lecture.getLector().equals(selectedLector)) {
                        listWithSelectedLector.add(lecture);
                    }
                }
                initRecyclerView(listWithSelectedLector);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initSpinnerWeeks() {
        weeksSpinner = findViewById(R.id.weeks_spinner);
        final Map<Integer, String> weeks = learningProgramProvider.provideWeeks();
        List<String> weeksList = new ArrayList<>(weeks.values());
        weeksList.add(POSITION_ALL, getResources().getString(R.string.all));
        final WeeksSpinnerAdapter adapter = new WeeksSpinnerAdapter(weeksList);
        weeksSpinner.setAdapter(adapter);

        weeksSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                weekTextView.setVisibility(View.VISIBLE);
                String selectedWeek = adapterView.getSelectedItem().toString();
                weekTextView.setText(selectedWeek);

                if (selectedWeek.equals(getResources().getString(R.string.all))) {
                    initRecyclerView(learningProgramProvider.provideLecture());
                    return;
                }

                int weekNumber = getKeyByValue(weeks, selectedWeek);
                List<Lecture> resultList = new ArrayList<>();
                for (Lecture lecture : learningProgramProvider.provideLecture()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(lecture.getDate());
                    int week = calendar.get(Calendar.WEEK_OF_YEAR);
                    if (week == weekNumber) resultList.add(lecture);
                }
                initRecyclerView(resultList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weekTextView = findViewById(R.id.week_number);

        initRecyclerView();
        initSpinnerLectors();
        initSpinnerWeeks();
    }
}
