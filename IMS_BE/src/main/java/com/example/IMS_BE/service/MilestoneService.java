package com.example.IMS_BE.service;

import com.example.IMS_BE.entity.Milestone;
import com.example.IMS_BE.entity.Project;

import java.util.List;

public interface MilestoneService {
    List<Milestone> getMilestoneByProject(Project project);
    List<Milestone> getAllMilestone();

    List<Milestone> findMilestonesByClassId(Long id);

    Milestone findById(Long id);
    Milestone saveMilestone(Milestone milestones);

    void deleteMilestone(Long id);
}
