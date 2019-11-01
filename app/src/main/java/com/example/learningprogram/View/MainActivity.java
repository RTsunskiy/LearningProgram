package com.example.learningprogram.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.learningprogram.Models.LearningProgramProvider;
import com.example.learningprogram.R;

public class MainActivity extends FragmentActivity {

    private LearningProgramProvider mLearningProgramProvider = new LearningProgramProvider();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.root_layout, MainFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}

