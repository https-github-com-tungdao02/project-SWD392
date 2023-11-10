package com.example.IMS_BE.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.IMS_BE.entity.Classes;
import com.example.IMS_BE.entity.Project;
import com.example.IMS_BE.entity.User;
import com.example.IMS_BE.service.IClassesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.IMS_BE.repository.IClassesRepository;

@Service
public class ClassesServiceImpl implements IClassesService {
    @Autowired
    private IClassesRepository _classesRepository;

    @Override
    public List<Classes> GetAllClasses() {
        return _classesRepository.findAll();
    }

    @Override
    public List<Classes> GetClassesById(long id) {
        throw new UnsupportedOperationException("Unimplemented method 'GetClassById'");
    }

    @Override
    public void AddClass(Classes classes) {
        _classesRepository.save(classes);
    }

    @Override
    public void DeleteClass(long id) {
        _classesRepository.deleteById(id);
    }

    @Override
    public void UpdateClass(Classes classes) {
        _classesRepository.save(classes);
    }

    @Override
    public Classes GetClassById(long id) {
        Optional<Classes> optionalClass = _classesRepository.findById(id);
        return optionalClass.orElse(null);
    }

    @Override
    public void CancelClass(long id) {
        Classes currentClass = GetClassById(id);
        currentClass.setStatus(0);
        UpdateClass(currentClass);
    }

    @Override
    public Classes getClassById(Long id) {
        return _classesRepository.findById(id).orElse(null);
    }

    @Override
    public void updateGeneral(Classes classToEdit) {
        _classesRepository.save(classToEdit);
    }

    @Override
    public void updateStudent(Classes classToEdit) {
        _classesRepository.save(classToEdit);
    }

    @Override
    public void updateMilestone(Classes classToEdit) {
        _classesRepository.save(classToEdit);
    }

    @Override
    public void updateSetting(Classes classToEdit) {
        _classesRepository.save(classToEdit);
    }

    @Override
    public Page<Classes> findAllClasses(Pageable pageable) {
        return _classesRepository.findAll(pageable);
    }
    @Override
    public List<Project> findProjectByClassId(long id){
        return _classesRepository.findProjectsByClassId(id);
    }

    public List<User> findUsersByClassId(long id){
        return _classesRepository.findUsersByClassId(id);
    }

    @Override
    public Page<Classes> findClassesByName(String searchString,Pageable pageable){
        return _classesRepository.findClassesByName(searchString,pageable);
    }
}
