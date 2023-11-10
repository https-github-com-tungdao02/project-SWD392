package com.example.IMS_BE.service.impl;

import com.example.IMS_BE.entity.Milestone;
import com.example.IMS_BE.entity.Project;
import com.example.IMS_BE.repository.IMilestoneRepository;
import com.example.IMS_BE.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MilestoneServiceImpl implements MilestoneService {
    @Autowired
    IMilestoneRepository milestoneRepository;
    @Override
    public List<Milestone> getMilestoneByProject(Project project) {
        return milestoneRepository.findAllByProject(project);
    }

    @Override
    public List<Milestone> getAllMilestone() {
        return milestoneRepository.findAll();
    }

    public List<Milestone> findMilestonesByClassId(Long classId) {
        return milestoneRepository.findByClassesId(classId);
    }

    public Milestone findById(Long id) {
        Optional<Milestone> optionalMilestone = milestoneRepository.findById(id);
        return optionalMilestone.orElse(null); // Hoặc trả về giá trị mặc định khác thay vì null nếu cần
    }

    public Milestone saveMilestone(Milestone milestones) {
        return  milestoneRepository.save(milestones);
    }

    public void deleteMilestone(Long id) {
        milestoneRepository.deleteById(id);
    }
}
