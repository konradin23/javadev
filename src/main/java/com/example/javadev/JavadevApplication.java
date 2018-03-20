package com.example.javadev;

import com.example.javadev.model.Lecture;
import com.example.javadev.model.User;
import com.example.javadev.repository.LectureRepository;
import com.example.javadev.repository.UserRepository;
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
import java.util.*;

@SpringBootApplication
public class JavadevApplication implements CommandLineRunner {

    @Autowired
    private ThymeleafProperties properties;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectureRepository lectureRepository;

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

//        Map<String, ArrayList<String>> attendanceList = new HashMap<>();
//
//        List<String> listOfStudentEmails = userRepository.findOnlyStudentEmails();
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
//            for (int lectureId:listOfLecturesId) {
//                if(innerList.contains(lectureId))
//                    listOfAttendance.add("Był");
//                else
//                    listOfAttendance.add("Nie był");
//            }
//            attendanceList.put(student, listOfAttendance);
//        }
    }
}