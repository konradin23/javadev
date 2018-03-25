package com.example.javadev;

import com.example.javadev.model.Lecture;

import java.util.Comparator;

public class LectureComparator implements Comparator<Lecture> {

    @Override
    public int compare(Lecture o1, Lecture o2) {
        return o1.getLectureDate().compareTo(o2.getLectureDate());
    }
}
