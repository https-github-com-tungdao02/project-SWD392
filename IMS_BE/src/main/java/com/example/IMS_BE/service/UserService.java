package com.example.IMS_BE.service;

import com.example.IMS_BE.entity.User;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {



    boolean isAdmin(String username);

    boolean checkLogin(String username, String password);

    public User getUserByUsername(String username);

}

