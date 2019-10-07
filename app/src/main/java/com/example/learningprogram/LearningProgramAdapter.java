package com.example.learningprogram;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningprogram.models.Lecture;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LearningProgramAdapter extends RecyclerView.Adapter<LearningProgramAdapter.LectureHolder> {

    private List<Lecture> lectureList;

    @NonNull
    @Override
    public LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lecture, parent, false);
        return new LectureHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LectureHolder holder, int position) {
        Lecture lecture = lectureList.get(position);
        holder.number.setText(lecture.getNumber());
        holder.date.setText(new SimpleDateFormat("dd.MM.yyyy").format(lecture.getDate()));
        holder.theme.setText(lecture.getTheme());
        holder.lector.setText(lecture.getLector());
    }

    @Override
    public int getItemCount() {
        return lectureList == null ? 0 : lectureList.size();
    }

    public void setLectureList(List<Lecture> lecture) {
        lectureList = lecture == null ? null : new ArrayList<>(lecture);
    }

    static class LectureHolder extends RecyclerView.ViewHolder {

        private final TextView number;
        private final TextView date;
        private final TextView theme;
        private final TextView lector;

        public LectureHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            date = itemView.findViewById(R.id.date);
            theme = itemView.findViewById(R.id.theme);
            lector = itemView.findViewById(R.id.lector);
        }
    }
}