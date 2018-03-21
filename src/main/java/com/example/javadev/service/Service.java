package com.example.javadev.service;

import java.util.ArrayList;
import java.util.Map;

public interface Service {
    Map<String, ArrayList<String>> createAttendanceList();
    Map<String, String> createStudentsList();
    int getNumberOfStudents();
}
