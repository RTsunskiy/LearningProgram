package com.example.learningprogram.Models;

import androidx.annotation.Nullable;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class LearningProgramProvider {


    public static final String LECTURES_URL = "http://landsovet.ru/learning_program.json";



    private List<Lecture> mLectures;

    public List<Lecture> provideLecture () {
        return mLectures;
    }

        public List<String> provideLectors () {
        Set<String> lectorsSet = new HashSet<>();
        for (Lecture lecture : mLectures) {
            lectorsSet.add(lecture.getLector());
        }
        return new ArrayList<>(lectorsSet);
    }

        public Map<Integer, String> provideWeeks () {
        Set<String> datesSet = new TreeSet<>();
        for (Lecture lecture : mLectures) {
            datesSet.add(lecture.getDate());
        }
        int count = 1;
        int prevWeek = 0;
        Map<Integer, String> weeksMap = new TreeMap<>();
        for (String date : datesSet) {
            Calendar calendar = Calendar.getInstance();
            Date date1 = new Date();
            try {
                date1 = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.setTime(date1);
            int week = calendar.get(Calendar.WEEK_OF_YEAR);
            if (week > prevWeek) {
                weeksMap.put(week, "Неделя " + count);
                count++;
                prevWeek = week;
            }
        }
        return weeksMap;
    }
        @Nullable
        public List<Lecture> loadLecturesFromWeb () {
        if (mLectures != null) {
            return mLectures;
        }
        InputStream is = null;
        try {
            final URL url = new URL(LECTURES_URL);
            URLConnection connection = url.openConnection();
            is = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            Lecture[] lectures = mapper.readValue(is, Lecture[].class);
            mLectures = Arrays.asList(lectures);
            return new ArrayList<>(mLectures);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
       return null;
    }
}



