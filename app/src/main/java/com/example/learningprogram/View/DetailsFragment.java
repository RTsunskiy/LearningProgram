package com.example.learningprogram.View;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learningprogram.Models.Lecture;
import com.example.learningprogram.R;

public class DetailsFragment extends Fragment {

    private static final String ARG_LECTURE = "ARG_LECTURE";

    public static DetailsFragment newInstance(@NonNull Lecture lecture) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_LECTURE, (Parcelable) lecture);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        Lecture lecture = getLectureFromArgs();
        ((TextView) view.findViewById(R.id.lector_number_tv)).setText(String.valueOf(lecture.getNumber()));
        ((TextView) view.findViewById(R.id.lector_name)).setText(lecture.getLector());
        ((TextView) view.findViewById(R.id.theme)).setText(lecture.getTheme());
        ((TextView) view.findViewById(R.id.date)).setText(lecture.getDate());
        ((TextView) view.findViewById(R.id.subtopic)).setText(lecture.getmSubtopics().toString());
        return view;

    }


    @NonNull
    private Lecture getLectureFromArgs() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            throw new IllegalStateException("Arguments must be set");
        }
        Lecture lecture = arguments.getParcelable(ARG_LECTURE);
        if (lecture == null) {
            throw new IllegalStateException("Lecture must be set");
        }
        return lecture;
    }
}
