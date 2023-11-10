package com.example.IMS_BE.service.impl;

import com.example.IMS_BE.entity.Classes;
import com.example.IMS_BE.entity.StudentClass;
import com.example.IMS_BE.entity.User;
import com.example.IMS_BE.repository.IStudentClassRepository;
import com.example.IMS_BE.service.IStudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentClassServiceImpl implements IStudentClassService {
    @Autowired
    IStudentClassRepository studentClassRepository;

    @Override
    public List<StudentClass> getClassesByUser(User user) {
        return studentClassRepository.findAllByStudent(user);
    }

    public StudentClass getStudentClassById(Long id) {
        return studentClassRepository.findById(id).orElse(null);
    }

    public void addStudentIntoClass(Classes classes, User user) {
    }

    public void kickStudent(long classId, long id) {
    }
}