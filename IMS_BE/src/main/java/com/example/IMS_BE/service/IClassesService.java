package com.example.IMS_BE.service;

import com.example.IMS_BE.entity.Classes;
import com.example.IMS_BE.entity.Project;
import com.example.IMS_BE.entity.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


public interface IClassesService {
    public List<Classes> GetAllClasses();
    public List<Classes> GetClassesById(long id);
    public void AddClass(Classes classes);
    public void DeleteClass(long id);
    public void UpdateClass(Classes classes);
    public Classes GetClassById(long id);
    public void CancelClass(long id);
    public Classes getClassById(Long id);
    public void updateGeneral(Classes classToEdit);
    public void updateStudent(Classes classToEdit);
    public void updateMilestone(Classes classToEdit);
    public void updateSetting(Classes classToEdit);
    public Page<Classes> findAllClasses(Pageable pageable);
//    public List<Project> findProjectByClassId(long id);
    List<User> findUsersByClassId(long id);
    List<Project> findProjectByClassId(long id);
    public Page<Classes> findClassesByName(String searchString,Pageable pageable);
}
