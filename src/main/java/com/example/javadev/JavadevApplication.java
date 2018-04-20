package com.example.javadev;

import com.example.javadev.model.Attendance;
import com.example.javadev.model.Lecture;
import com.example.javadev.model.User;
import com.example.javadev.repository.AttendanceRepository;
import com.example.javadev.repository.LectureRepository;
import com.example.javadev.repository.UserRepository;
import com.example.javadev.service.Service;
import com.fasterxml.jackson.databind.util.TypeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.awt.print.Book;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

@SpringBootApplication
public class JavadevApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(JavadevApplication.class);

    @Autowired
    AttendanceRepository attendanceRepository;

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
        Attendance a = attendanceRepository.findAttendanceByUserIdAndLectureId(3,21);
        System.out.println(a.getAttendanceId());
    }
}