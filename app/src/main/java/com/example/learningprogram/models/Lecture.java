package com.example.learningprogram.models;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lecture {
    private static final String patternDate = "dd.MM.yyyy";

    private final String number;
    private final Date date;
    private final String theme;
    private final String lector;

    public Lecture(@NonNull String number, @NonNull String date, @NonNull String theme, @NonNull String lector) throws ParseException {
        this.number = number;
        this.theme = theme;
        this.lector = lector;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDate);
        this.date = simpleDateFormat.parse(date);
    }

    public String getNumber() {
        return number;
    }

    public Date getDate() {
        return date;
    }

    public String getTheme() {
        return theme;
    }

    public String getLector() {
        return lector;
    }
}
