package com.example.IMS_BE.repository;

import com.example.IMS_BE.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IMS_BE.entity.Milestone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMilestoneRepository extends JpaRepository<Milestone,Long>{
    
List<Milestone> findAllByProject(Project project);
    List<Milestone> findByClassesId(Long classId);
}
