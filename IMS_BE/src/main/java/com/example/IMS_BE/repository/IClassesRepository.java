package com.example.IMS_BE.repository;

import com.example.IMS_BE.entity.Classes;
import com.example.IMS_BE.entity.Project;
import com.example.IMS_BE.entity.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassesRepository extends JpaRepository<Classes,Long> {
    @Query("SELECT c FROM Project c WHERE c.classes.id = :classId")

    List<Project> findProjectsByClassId(@Param("classId") Long classId);

    @Query("SELECT sc.student FROM StudentClass sc WHERE sc.classes.id = :classId")
    List<User> findUsersByClassId(@Param("classId") Long classId);

    @Query("SELECT c FROM Classes c WHERE c.name LIKE %:searchString%")
    Page<Classes> findClassesByName(@Param("searchString") String searchString, Pageable pageable);

}
