package com.example.javadev.service;

import com.example.javadev.model.Lecture;
import com.example.javadev.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Service {
    Map<User, ArrayList<String>> createAttendanceList();
    List<User> createStudentsList();
    int getNumberOfStudents();
    int getNumberOfLecturesAttendedByStudent(int userId);
    int getNumberOfAllLectures();
    List<String> createListOfStudentsEmails();
    int getNumberOfStudentsAttendedByLectureId(int lectureId);
    List<Integer> createListNumberOfStudentsPresentOnLecture();
    List<Lecture> createListOfLecturesSortedByDate();
    List<Integer> createListOfLecturesIdSortedByDate();
    boolean isNumberOfLecturesMoreThan8();
}
