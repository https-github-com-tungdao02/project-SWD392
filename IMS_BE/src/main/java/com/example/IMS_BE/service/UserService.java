package com.example.IMS_BE.service;

import com.example.IMS_BE.entity.Project;
import com.example.IMS_BE.entity.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.*;
import java.util.Optional;

public interface UserService {
    public User getUserByEmail(String username);

    List<User> getAllUsers();

    boolean isAdmin(String username);

    boolean checkLogin(String username, String password);

    Optional<User> findUserById(Long id);
    Optional<User> findById(int id);
    List<User> findUserList();
    List<User> findManagerList();
    public User registerUser(String username, String email, String phone, String password);
    public User getUserByUsername(String username);
    public String getRolesByUserName(String userName);

}

