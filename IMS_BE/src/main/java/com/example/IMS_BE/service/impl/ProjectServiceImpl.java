package com.example.IMS_BE.service.impl;

import com.example.IMS_BE.entity.Classes;
import com.example.IMS_BE.entity.Project;
import com.example.IMS_BE.repository.IProjectRepository;
import com.example.IMS_BE.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements IProjectService {

    private final IProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Các phương thức xử lý logic dự án
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).get();
    }

    public Project saveProject(Project project) {
      return   projectRepository.save(project);
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }


    @Override
    public List<Project> getProjectByClassess(Classes classes) {
        return projectRepository.findAllByClasses(classes);
    }

    public List<Project> getProjectsByClassesId(Long classId) {
        return projectRepository.findByClassesId(classId);
    }


}