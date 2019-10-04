package com.example.learningprogram.models;

import androidx.annotation.NonNull;

public class Lecture {
    private final String mNumber;
    private final String mDate;
    private final String mTheme;
    private final String mLector;

    public String getmNumber() {
        return mNumber;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmTheme() {
        return mTheme;
    }

    public String getmLector() {
        return mLector;
    }

    public Lecture(
            @NonNull String mNumber,
            @NonNull String mDate,
            @NonNull String mTheme,
            @NonNull String mLector)
    {
        this.mNumber = mNumber;
        this.mDate = mDate;
        this.mTheme = mTheme;
        this.mLector = mLector;
    }
}
