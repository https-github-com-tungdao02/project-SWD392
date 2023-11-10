package com.example.IMS_BE.service.impl;


import com.example.IMS_BE.entity.Issue;
import com.example.IMS_BE.entity.Setting;
import com.example.IMS_BE.entity.User;
import com.example.IMS_BE.repository.SettingRepository;
import com.example.IMS_BE.repository.UserRepository;
import com.example.IMS_BE.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.*;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    SettingRepository settingRepository;

    public Optional<User> findByPhone(String phone) {
        return Optional.ofNullable(userRepository.findByPhone(phone));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public java.util.List<User> findAllByRole(long roles) {
        return userRepository.findByRoleId(roles);
    }

    @Override

    public User getUserByEmail(String username) {
        return userRepository.findByEmail(username);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }
    @Override
    public boolean isAdmin(String username) {
        String roles = userRepository.findRolesByPhone(username);
        String roles2 = userRepository.findRolesByEmail(username);
        return (roles != null && roles.contains("admin")) || (roles2 != null && roles2.contains("admin"));
    }


    @Override
    public boolean checkLogin(String username, String password) {
        Optional<User> optionalUser = findByPhone(username);
        Optional<User> optionalUser2 = findByEmail(username);
        if ((optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) || (optionalUser2.isPresent() && optionalUser2.get().getPassword().equals(password))) {
            return true;
        }

        return false;
    }



    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String getRolesByUserName(String userName) {
        return userRepository.findRolesByEmail(userName);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(userRepository.findById(id));
    }

    @Override
    public List<User> findUserList() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public List<User> findManagerList() {
        Optional<Setting> optionalSetting= settingRepository.findById((long) 5);
        if(optionalSetting.isPresent()){
            return userRepository.findAllByRole(optionalSetting.get());
        }
        else{
            return null;
        }
    }


    @Override
    public User registerUser(String username, String email, String phone, String password) {
        if (userRepository.findByUsername(username) != null || userRepository.findByEmail(email) != null || userRepository.findByEmail(phone) != null) {
            throw new RuntimeException("Người dùng đã tồn tại");
        }
        try {
            Setting defaultRole = settingRepository.findBySettingId(2L);
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPhone(phone);
            newUser.setPassword(password);
            newUser.setRole(defaultRole);

            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi đăng ký người dùng: " + e.getMessage());
        }
    }




//    public User registerUser(String username, String email, String phone, String password) {
//        if (userRepository.findByUsername(username) != null || userRepository.findByEmail(email) != null || userRepository.findByEmail(phone) != null) {
//            throw new RuntimeException("Người dùng đã tồn tại");
//        }
//        try {
//          //  Setting defaultRole = settingRepository.findBySettingId(2L);
//            User newUser = new User();
//            newUser.setUsername(username);
//            newUser.setEmail(email);
//            newUser.setPhone(phone);
//            newUser.setPassword(password);
//            newUser.setRole(defaultRole);
//
//            return userRepository.save(newUser);
//        } catch (Exception e) {
//            throw new RuntimeException("Lỗi khi đăng ký người dùng: " + e.getMessage());
//        }
    }






