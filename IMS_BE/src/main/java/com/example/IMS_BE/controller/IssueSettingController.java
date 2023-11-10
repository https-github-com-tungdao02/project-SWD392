package com.example.IMS_BE.controller;

import com.example.IMS_BE.entity.*;
import com.example.IMS_BE.repository.IIssueSettingRepository;
import com.example.IMS_BE.service.*;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/issue_setting")
public class IssueSettingController {
    @Autowired
    private IIssueSettingRepository issueSettingRepository;
    @Autowired
    private IssueSettingService issueSettingService;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IClassesService classesService;
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/list")
    public String index(Model model) {
        List<IssueSetting> issueSettings = issueSettingService.getAllIssueSetting();
        model.addAttribute("issueSettings", issueSettings);

        return "IssueSetting/issuesetting";
    }

    @GetMapping("/add")
    public String issueSettingAdd(Model model){
        List<Project> projectList = projectService.getAllProjects();
        List<Subject> subjectList = subjectService.getAllSubject();
        List<Classes> classesList = classesService.GetAllClasses();
        model.addAttribute("projectList",projectList );
        model.addAttribute("subjectList",subjectList );
        model.addAttribute("classesList",classesList );
        IssueSetting issueSetting = new IssueSetting();
        model.addAttribute("issueSetting", issueSetting);

        return "IssueSetting/issuesettingadd";
    }

    @PostMapping("/add")
    public String saveIssueSetting(IssueSetting issueSetting){
        issueSettingService.saveIssueSetting(issueSetting);
        return "redirect:/issue_setting/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteIssueSetting(@RequestParam("id") Long issueSettingId, Model model) {
        issueSettingService.deleteIssueSettingById(issueSettingId);
        return "redirect:/issue_setting/list";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Long issueSettingId, Model model) {
        List<Project> projectList = projectService.getAllProjects();
        List<Subject> subjectList = subjectService.getAllSubject();
        List<Classes> classesList = classesService.GetAllClasses();
        model.addAttribute("projectList",projectList );
        model.addAttribute("subjectList",subjectList );
        model.addAttribute("classesList",classesList );
        Optional<IssueSetting> issueSettingEdit = issueSettingService.findIssueSettingById(issueSettingId);
        issueSettingEdit.ifPresent(issueSetting -> model.addAttribute("issueSetting", issueSetting));
        return "IssueSetting/issuesettingdetails";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(IssueSetting issueSetting) {
        if (issueSetting.getId() != null) {
            IssueSetting existingIssueSetting = issueSettingService.findIssueSettingById(issueSetting.getId()).orElse(null);
            if (existingIssueSetting != null) {
                existingIssueSetting.setName(issueSetting.getName());
                existingIssueSetting.setType(issueSetting.getType());
                issueSettingService.saveIssueSetting(existingIssueSetting);
            }
        } else {
            issueSettingService.saveIssueSetting(issueSetting);
        }
        return "redirect:/issue_setting/list";
    }




    @ResponseBody
    @GetMapping("/api/getbyprojectandtype")
    public ResponseEntity<List<IssueSetting>> getByProjectAndType(@PathParam("projectid") Long id,@PathParam("type")String type){
        return new ResponseEntity<List<IssueSetting>> (issueSettingService.getIsssueSettingByTypeAndProject(id,type), HttpStatus.OK);

    }
//    @ResponseBody
//    @GetMapping("/api/getbyproject")
//    public ResponseEntity<List<IssueSetting>> getByProject(@RequestParam ("projectid") Long id,@RequestParam("type" )String type){
//        List<IssueSetting> list = issueSettingService.getByProject(id,type);
////        for (IssueSetting issuesetting: list) {
////            if(!issuesetting.getType().equals(type)){
////                list.remove(issuesetting);
////            }
////        }
//        if (list == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<List<IssueSetting>>(list, HttpStatus.OK);
//    }

    }


