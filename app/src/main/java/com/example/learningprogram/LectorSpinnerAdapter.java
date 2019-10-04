package com.example.learningprogram;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LectorSpinnerAdapter extends BaseAdapter {

    private final List<String> mLectors;

    public LectorSpinnerAdapter(List<String> mLectors) {
        this.mLectors = mLectors;
    }


    @Override
    public int getCount() {
        return mLectors.size();
    }

    @Override
    public String getItem(int position) {
        return mLectors.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.mLectorName.setText(getItem(position));
        return convertView;
    }

    private class ViewHolder {
        private final TextView mLectorName;

        private ViewHolder(View view) {
            mLectorName = view.findViewById(android.R.id.text1);
        }
    }
}