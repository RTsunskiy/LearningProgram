package com.example.learningprogram;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningprogram.models.LearningProgramProvider;
import com.example.learningprogram.models.Lecture;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainFragment extends Fragment {

    private LearningProgramProvider learningProgramProvider = new LearningProgramProvider();
    private static final int POSITION_ALL = 0;
    private RecyclerView recyclerView;
    private Spinner lectorsSpinner;
    private Spinner weeksSpinner;
    private TextView weekTextView;
    private View rootView;


    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        weekTextView = rootView.findViewById(R.id.week_number);

        return rootView;
    }


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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Lecture> lectures = learningProgramProvider.provideLecture();
        if (lectures == null) {
            new LoadLecturesTask(this).execute();
        } else {
            initRecyclerView(lectures);
            initRecyclerView();
            initSpinnerWeeks();
            initSpinnerLectors();
        }
    }

    private void initRecyclerView() {
        recyclerView = rootView.findViewById(R.id.learning_program_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        LearningProgramAdapter adapter = new LearningProgramAdapter();
        adapter.setLectureList(learningProgramProvider.provideLecture());
        DividerItemDecoration divider = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView(List<Lecture> lectureList) {
        LearningProgramAdapter adapter = new LearningProgramAdapter();
        adapter.setLectureList(lectureList);
        recyclerView.setAdapter(adapter);
    }

    private void initSpinnerLectors() {
        lectorsSpinner = rootView.findViewById(R.id.lectors_spinner);
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
        weeksSpinner = rootView.findViewById(R.id.weeks_spinner);
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
                    String date1 = lecture.getDate();
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    calendar.setTime(date);
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

    private static class LoadLecturesTask extends AsyncTask<Void, Void, List<Lecture>> {
        private final WeakReference<MainFragment> mFragmentRef;
        private final LearningProgramProvider mProvider;


        private LoadLecturesTask(@NonNull MainFragment fragment) {
            mFragmentRef = new WeakReference<>(fragment);
            mProvider = fragment.learningProgramProvider;

        }


        @Override
        protected void onPreExecute() {
            MainFragment fragment = mFragmentRef.get();

        }


        @Override
        protected List<Lecture> doInBackground(Void... arg) {
            return mProvider.loadLecturesFromWeb();
        }

        @Override
        protected void onPostExecute(List<Lecture> lectures) {
            MainFragment fragment = mFragmentRef.get();
            if (lectures == null) {
                Toast.makeText(fragment.requireContext(), "Илюха Пидарас", Toast.LENGTH_SHORT).show();
            } else {
                fragment.initRecyclerView(lectures);
                fragment.initRecyclerView();
                fragment.initSpinnerLectors();
                fragment.initSpinnerWeeks();
            }
        }
    }

}
