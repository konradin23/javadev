package com.example.javadev;

import com.example.javadev.model.Lecture;
import com.example.javadev.model.User;
import com.example.javadev.repository.LectureRepository;
import com.example.javadev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.transaction.Transactional;
import java.util.Date;

@SpringBootApplication
public class JavadevApplication {

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

//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//
//        User user = userRepository.findOne("abc@gmail.com");
//        Lecture lecture1 = lectureRepository.findOne(24);
//        Lecture lecture2 = lectureRepository.findOne(21);
//
//        user.getLectures().add(lecture1);
//        user.getLectures().add(lecture2);
//
//        lecture1.getUsers().add(user);
//        lecture2.getUsers().add(user);
//
//        userRepository.save(user);
//
//        // =======================================
//
//    }
}