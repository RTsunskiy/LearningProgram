package com.example.learningprogram.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningprogram.Models.Lecture;
import com.example.learningprogram.R;
import com.example.learningprogram.View.DetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class LearningProgramAdapter extends RecyclerView.Adapter<LearningProgramAdapter.LectureHolder> {

    private List<Lecture> lectureList;
    private FragmentActivity context;

    public LearningProgramAdapter(FragmentActivity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lecture, parent, false);
        return new LectureHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LectureHolder holder, int position) {
        final Lecture lecture = lectureList.get(position);
        holder.number.setText(lecture.getNumber());
        holder.date.setText(lecture.getDate());
        holder.theme.setText(lecture.getTheme());
        holder.lector.setText(lecture.getLector());
        holder.theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
                        .replace(R.id.root_layout, DetailsFragment.newInstance(lecture))
                        .commit();
            }
        });

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