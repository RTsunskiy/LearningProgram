package com.example.learningprogram.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LearningProgramProvider {



    private List<Lecture> mLectures = Arrays.asList(
            new Lecture("1","24.09.2019","Вводное занятие","Соколов"),
            new Lecture("2","26.09.2019","View, Layouts","Соколов"),
            new Lecture("3" ,"28.09.2019","Drawables","Соколов"),
            new Lecture("4" ,"01.10.2019","Activity","Сафарян"),
            new Lecture("5" ,"03.10.2019","Адаптеры","Чумак"),
            new Lecture("6" ,"05.10.2019","UI: практика","Кудрявцев"),
            new Lecture("7" ,"08.10.2019","Custom View","Кудрявцев"),
            new Lecture("8" ,"10.10.2019","Touch events","Чумак")
    );

    public List<Lecture> provideLecture() {
        return mLectures;
    }

    public List<String> provideLectors() {
        Set<String> lectorsSet = new HashSet<>();
        for (Lecture lecture : mLectures) {
            lectorsSet.add(lecture.getmLector());
        }
        return new ArrayList<>(lectorsSet);
    }
}
