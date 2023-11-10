package com.example.IMS_BE.repository;

import com.example.IMS_BE.entity.Classes;
import com.example.IMS_BE.entity.Project;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProjectRepository extends JpaRepository<Project,Long> {
List<Project> findAllByClasses(Classes classes);

    List<Project> findByClassesId(Long classId);

    // public java.util.List<Project> getProjectByClassId(Long id);

}
