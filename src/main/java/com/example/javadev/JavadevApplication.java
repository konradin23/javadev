package com.example.javadev;

import com.example.javadev.model.Lecture;
import com.example.javadev.model.User;
import com.example.javadev.repository.LectureRepository;
import com.example.javadev.repository.UserRepository;
import com.example.javadev.service.Service;
import com.fasterxml.jackson.databind.util.TypeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

@SpringBootApplication
public class JavadevApplication implements CommandLineRunner {

    @Autowired
    private ThymeleafProperties properties;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    Service service;

    @Value("${student.present}")
    private String studentPresent;

    @Value("${student.absent}")
    private String studentAbsent;

    @Value("${spring.thymeleaf.templates_root:}")
    private String templatesRoot;

    public static void main(String[] args) {
        SpringApplication.run(JavadevApplication.class, args);
    }

    @Bean
    public ITemplateResolver defaultTemplateResolver() {
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setSuffix(properties.getSuffix());
        resolver.setPrefix(templatesRoot);
        //resolver.setTemplateMode(properties.getMode());
        resolver.setCacheable(properties.isCache());
        return resolver;
    }

    @Override
    public void run(String... args) {
//        HashMap<User, ArrayList<String>> attendanceList = new HashMap<>();
//        TreeMap<User, ArrayList<String>> sortedAttendanceList = new TreeMap<>();
//
//        List<Integer> listOfStudentIds = userRepository.findOnlyStudentIds();
//        List<Integer> listOfLecturesId = service.createListOfLecturesIdSortedByDate();
//
//        for (int studentId : listOfStudentIds) {
//            //za kazdym razem robie nową liste, bo póżniej Map prawdopodobnie? potrzebuje cały czas referencje do tej listy
//            User student = new User();
//            student = userRepository.findByUserId(studentId);
//            student.setNumberOfLecturesAttended(lectureRepository.findAllLectureIdsAttendedByStudent(studentId).size());
//            ArrayList<Integer> innerList = new ArrayList<>();
//            ArrayList<String> listOfAttendance = new ArrayList<>();
//            listOfAttendance.clear();
//            innerList.clear();
//
//            innerList.addAll(lectureRepository.findAllLectureIdsAttendedByStudent(studentId));
//            for (int lectureId : listOfLecturesId) {
//                if (innerList.contains(lectureId))
//                    listOfAttendance.add(studentPresent);
//                else
//                    listOfAttendance.add(studentAbsent);
//            }
//
//            attendanceList.put(student, listOfAttendance);
//        }

    }
}