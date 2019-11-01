package com.example.learningprogram.Models;



import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.ArrayList;

import java.util.List;

public class Lecture implements Parcelable {
    private static final String patternDate = "dd.MM.yyyy";

    private final String mNumber;
    private final String mDate;
    private final String mTheme;
    private final String mLector;
    private final List<String> mSubtopics;

    @JsonCreator
    public Lecture(
            @JsonProperty("number") String number,
            @JsonProperty("date") @NonNull String date,
            @JsonProperty("theme") @NonNull String theme,
            @JsonProperty("lector") @NonNull String lector,
            @JsonProperty("subtopics") @NonNull List<String> subtopics) {
        mNumber = number;
        mTheme = theme;
        mDate = date;
        mLector = lector;
        mSubtopics = new ArrayList<>(subtopics);
    }



    public String getNumber() {
        return mNumber;
    }

    public String getDate() {
        return mDate;
    }

    public String getTheme() {
        return mTheme;
    }

    public String getLector() {
        return mLector;
    }

    public List<String> getmSubtopics() { return mSubtopics; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
