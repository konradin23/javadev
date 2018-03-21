package com.example.javadev.service;

import com.example.javadev.repository.LectureRepository;
import com.example.javadev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("service")
public class ServiceImpl implements com.example.javadev.service.Service {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Override
    public TreeMap<String, ArrayList<String>> createAttendanceList() {

        TreeMap<String, ArrayList<String>> attendanceList = new TreeMap<>();

        List<String> listOfStudentEmails = userRepository.findOnlyStudentEmails();
        List<Integer> listOfLecturesId = lectureRepository.getAllLecturesId();

        for (String student : listOfStudentEmails) {
            //za kazdym razem robie nową liste, bo póżniej Map prawdopodobnie? potrzebuje cały czas referencje do tej listy
            ArrayList<Integer> innerList = new ArrayList<>();
            ArrayList<String> listOfAttendance = new ArrayList<>();
            listOfAttendance.clear();
            innerList.clear();

            innerList.addAll(lectureRepository.findAllAttendedLecturesByStudent(student));
            for (int lectureId : listOfLecturesId) {
                if (innerList.contains(lectureId))
                    listOfAttendance.add("/img/check.png");
                else
                    listOfAttendance.add("/img/error.png");
            }
            attendanceList.put(student, listOfAttendance);
        }
        return attendanceList;
    }

    @Override
    public TreeMap<String, String> createStudentsList() {

        TreeMap<String, String> studentsList = new TreeMap<>();

        List<String> listOfStudentsEmails = userRepository.findOnlyStudentEmails();
        List<String> listOfStudentNames = new ArrayList<>();
        for (String email : listOfStudentsEmails) {
            String name = userRepository.findStudentsNameByEmail(email);
            listOfStudentNames.add(name);
            studentsList.put(email, name);
        }
        return studentsList;
    }

    public int getNumberOfStudents(){
        return userRepository.findOnlyStudentEmails().size();
    }
}


