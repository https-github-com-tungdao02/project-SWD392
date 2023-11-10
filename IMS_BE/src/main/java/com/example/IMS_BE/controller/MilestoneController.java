package com.example.IMS_BE.controller;

import com.example.IMS_BE.entity.*;
import com.example.IMS_BE.service.IProjectService;
import com.example.IMS_BE.service.MilestoneService;
import com.example.IMS_BE.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/milestone")
public class MilestoneController {
    @Autowired
    MilestoneService milestoneService;
    @Autowired
    IProjectService projectService;

    @Autowired
    private ProjectServiceImpl projectService2;
    @ResponseBody
    @GetMapping("/api/milestoneByProject/{id}")
    public ResponseEntity<List<Milestone>> getMilestoneByProject(@PathVariable Long id){
        return new ResponseEntity<List<Milestone>> ( milestoneService.getMilestoneByProject(projectService.getProjectById(id)), HttpStatus.OK);
    }



    @GetMapping("/milestones/edit/{id}")
    public String editMilestoneForm(@PathVariable Long id, Model model) {
        Milestone milestone = milestoneService.findById(id); // Sử dụng service để lấy Milestone từ cơ sở dữ liệu
        model.addAttribute("milestoneform", milestone);
        return "Project/projectmilestones"; // Trả về view để chỉnh sửa Milestone
    }

    @GetMapping("/milestones/add/{id}")
    public String addMilestoneForm(@PathVariable Long id, Model model) {
        Milestone milestone = milestoneService.findById(id); // Sử dụng service để lấy Milestone từ cơ sở dữ liệu
        model.addAttribute("milestoneform", milestone);

        List<Project> classProject =  projectService2.getProjectsByClassesId(id);
        model.addAttribute("classProject", classProject);

        return "Project/addprojectmilestones"; // Trả về view để chỉnh sửa Milestone
    }



    @PostMapping("/saveMilestone")
    public String save(@ModelAttribute("milestoneform") Milestone milestones) {
        milestoneService.saveMilestone(milestones);
        return "redirect:/classes/edit/"+ milestones.getClasses().getId();
    }


    @GetMapping("/delete/{id}")
    public String deleteMilestone(@PathVariable Long id) {
        milestoneService.deleteMilestone(id);
        return "redirect:/classes/classList";
    }



}
