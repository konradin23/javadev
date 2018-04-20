package com.example.javadev.service;

import com.example.javadev.LectureComparator;
import com.example.javadev.model.Attendance;
import com.example.javadev.model.Lecture;
import com.example.javadev.model.User;
import com.example.javadev.repository.AttendanceRepository;
import com.example.javadev.repository.LectureRepository;
import com.example.javadev.repository.RoleRepository;
import com.example.javadev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@PropertySource("classpath:application.properties")
@Service("service")
public class ServiceImpl implements com.example.javadev.service.Service {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Value("${student.present}")
    private String studentPresent;

    @Value("${student.absent}")
    private String studentAbsent;

    @Override
    public TreeMap<User, ArrayList<String>> createAttendanceList() {

        TreeMap<User, ArrayList<String>> attendanceList = new TreeMap<>();

        List<Integer> listOfStudentIds = userRepository.findOnlyStudentIds();
        List<Integer> listOfLecturesId = createListOfLecturesIdSortedByDate();

        for (int studentId : listOfStudentIds) {
            //za kazdym razem robie nową liste, bo póżniej Map prawdopodobnie? potrzebuje cały czas referencje do tej listy
            User student = new User();
            student = userRepository.findByUserId(studentId);
            student.setNumberOfLecturesAttended(lectureRepository.findAllLectureIdsAttendedByStudent(studentId).size());
            ArrayList<Integer> innerList = new ArrayList<>();
            ArrayList<String> listOfAttendance = new ArrayList<>();
            listOfAttendance.clear();
            innerList.clear();

            innerList.addAll(lectureRepository.findAllLectureIdsAttendedByStudent(studentId));
            for (int lectureId : listOfLecturesId) {
                if (innerList.contains(lectureId))
                    listOfAttendance.add(studentPresent);
                else
                    listOfAttendance.add(studentAbsent);
            }

            attendanceList.put(student, listOfAttendance);
        }
        return attendanceList;
    }

    @Override
    public List<User> createStudentsList() {

        List<User> studentsList = new ArrayList<>();
        List<Integer> listOfStudentsIds = userRepository.findOnlyStudentIds();

        for (int UserId : listOfStudentsIds) {
            User user = userRepository.findUserByUserId(UserId);
            studentsList.add(user);
        }
        return studentsList;
    }

    @Override
    public int getNumberOfStudents() {
        return userRepository.findOnlyStudentIds().size();
    }

    @Override
    public int getNumberOfLecturesAttendedByStudent(int userId) {
        return lectureRepository.findAllLectureIdsAttendedByStudent(userId).size();
    }

    @Override
    public int getNumberOfAllLectures() {
        return lectureRepository.getAllLecturesId().size();
    }


    @Override
    public List<String> createListOfStudentsEmails() {

        List<Integer> listOfStudentsIds = userRepository.findOnlyStudentIds();
        List<String> listOfStudentsEmails = new ArrayList<>();
        for (int userId : listOfStudentsIds) {
            listOfStudentsEmails.add(userRepository.findStudentsEmailByUserId(userId));
        }
        return listOfStudentsEmails;
    }

    @Override
    public int getNumberOfStudentsAttendedByLectureId(int lectureId) {
        return lectureRepository.findAllStudentsIdsWhoAttendedLecture(lectureId).size();
    }

    @Override
    public List<Integer> createListNumberOfStudentsPresentOnLecture() {
        List<Integer> listNumberOfStudentsPresentOnLecture = new ArrayList<>();
        for (Lecture lecture : createListOfLecturesSortedByDate()) {
            listNumberOfStudentsPresentOnLecture.add(
                    lectureRepository.findAllStudentsIdsWhoAttendedLecture(lecture.getLectureId()).size());
        }
        return listNumberOfStudentsPresentOnLecture;
    }

    @Override
    public List<Lecture> createListOfLecturesSortedByDate() {

        List<Lecture> listOfLectures = lectureRepository.findAll();
        Collections.sort(listOfLectures, (new LectureComparator()));
        return listOfLectures;
    }

    @Override
    public List<Integer> createListOfLecturesIdSortedByDate() {
        List<Lecture> listOfLectures = createListOfLecturesSortedByDate();
        List<Integer> listOfLecturesIdSortedByDate = new ArrayList<>();
        for (Lecture lecture : listOfLectures) {
            listOfLecturesIdSortedByDate.add(lecture.getLectureId());
        }
        return listOfLecturesIdSortedByDate;
    }

    @Override
    public boolean isNumberOfLecturesMoreThan8() {
        if (lectureRepository.count() >= 8)
            return true;
        return false;
    }

    @Override
    public List<Integer> createListOfLectureAttendanceByStudent(int userId) {

        List<Attendance> listOfAttendanceByStudent = attendanceRepository.findAllAttendanceByStudent(userId);
        List<Integer> listOfLectureAttendanceByStudent = new ArrayList<>();
        for (Attendance attendance:listOfAttendanceByStudent) {
            listOfLectureAttendanceByStudent.add(attendance.getAttendanceStatus());
        }
        return listOfLectureAttendanceByStudent;
    }

}


