package com.example.javadev.service;

import com.example.javadev.model.User;

public interface UserService {
    public User findUserByEmail(String email);
//    public void saveUser(User user);
}
