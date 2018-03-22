package com.example.javadev.service;

import com.example.javadev.model.User;
import com.example.javadev.repository.LectureRepository;
import com.example.javadev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;

@PropertySource("classpath:application.properties")
@Service("service")
public class ServiceImpl implements com.example.javadev.service.Service {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Value("${student.present}")
    private String studentPresent;

    @Value("${student.absent}")
    private String studentAbsent;

//    @Override
//    public TreeMap<String, ArrayList<String>> createAttendanceList() {
//
//        TreeMap<String, ArrayList<String>> attendanceList = new TreeMap<>();
//
//        List<String> listOfStudentEmails = userRepository.findOnlyStudentIds();
//        List<Integer> listOfLecturesId = lectureRepository.getAllLecturesId();
//
//        for (String student : listOfStudentEmails) {
//            //za kazdym razem robie nową liste, bo póżniej Map prawdopodobnie? potrzebuje cały czas referencje do tej listy
//            ArrayList<Integer> innerList = new ArrayList<>();
//            ArrayList<String> listOfAttendance = new ArrayList<>();
//            listOfAttendance.clear();
//            innerList.clear();
//
//            innerList.addAll(lectureRepository.findAllAttendedLecturesByStudent(student));
//            for (int lectureId : listOfLecturesId) {
//                if (innerList.contains(lectureId))
//                    listOfAttendance.add(studentPresent);
//                else
//                    listOfAttendance.add(studentAbsent);
//            }
//            attendanceList.put(userRepository.findStudentsNameByEmail(student), listOfAttendance);
//        }
//        return attendanceList;
//    }

    @Override
    public TreeMap<String, String> createStudentsList() {

        TreeMap<String, String> studentsList = new TreeMap<>();

        List<Integer> listOfStudentsIds = userRepository.findOnlyStudentIds();

        for (int UserId : listOfStudentsIds) {
            User user = userRepository.findUserByUserId(UserId);
            String name = user.getFirstName() + " " + user.getLastName();
            String email = user.getEmail();
            studentsList.put(email, name);
        }

        return studentsList;
    }

    @Override
    public int getNumberOfStudents() {
        return userRepository.findOnlyStudentIds().size();
    }

    @Override
    public int getNumberOfLecturesAttendedByStudent(int userId){
        return  lectureRepository.findAllLectureIdsAttendedByStudent(userId).size();
    }
    @Override
    public int getNumberOfAllLectures(){
        return lectureRepository.getAllLecturesId().size();
    }

//    public List<String> getAllStudentsEmails(){
//
//    }
}


