package com.example.javadev.repository;

import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    UserRepository userRepository;

    public void addLecture() {

    }
}
