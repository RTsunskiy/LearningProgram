package com.example.learningprogram;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class WeeksSpinnerAdapter extends BaseAdapter {

    private final List<String> weeksList;

    public WeeksSpinnerAdapter(@NonNull List<String> weeksList) {
        this.weeksList = weeksList;
    }

    @Override
    public int getCount() {
        return weeksList.size();
    }

    @Override
    public String getItem(int i) {
        return weeksList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            WeeksSpinnerAdapter.ViewHolder viewHolder = new WeeksSpinnerAdapter.ViewHolder(view);
            view.setTag(viewHolder);
        }
        WeeksSpinnerAdapter.ViewHolder holder = (WeeksSpinnerAdapter.ViewHolder) view.getTag();
        holder.week.setText(getItem(i));
        return view;
    }

    private class ViewHolder {
        private TextView week;

        private ViewHolder(View view) {
            this.week = view.findViewById(android.R.id.text1);
        }
    }
}