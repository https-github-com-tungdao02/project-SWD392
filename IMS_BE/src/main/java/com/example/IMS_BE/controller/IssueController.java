package com.example.IMS_BE.controller;

import com.example.IMS_BE.entity.*;
import com.example.IMS_BE.repository.IIssueSettingRepository;
import com.example.IMS_BE.service.*;

import com.example.IMS_BE.utils.PieChartData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    IssueService issueService;
    @Autowired
    UserService userService;
    @Autowired
    IStudentClassService studentClassService;
    @Autowired
    IClassesService classesService;
    @Autowired
    IProjectService projectService;
    @Autowired
    MilestoneService milestoneService;
    @Autowired
    IssueSettingService issueSettingService;
    @Autowired
    IStudentProjectService studentProjectService;
    @GetMapping("/student")
    public String getListIssue(Model model, HttpSession session, HttpServletRequest request){
        session=request.getSession();
        String email=session.getAttribute("USERNAME").toString();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user",user);
        List<Issue> list = issueService.getAllIssueByAssignee(user);
        model.addAttribute("list",list);
        return "Issue/issuelist";
    }
    @GetMapping("/student/add")
    public String addIssue(Model model,HttpSession session, HttpServletRequest request){
        session=request.getSession();
        String email=session.getAttribute("USERNAME").toString();
        User user = userService.getUserByEmail(email);
        model.addAttribute("assignerissue",user);
        List<StudentClass> listClass = studentClassService.getClassesByUser(user);
        List<Project> projectList= projectService.getAllProjects();
        List<Milestone> milestoneList=milestoneService.getAllMilestone();
        List<IssueSetting> statusList=issueSettingService.getIsssueSettingByTypeAndProject((long) 1,"Status");
        List<IssueSetting> processList=issueSettingService.getIsssueSettingByTypeAndProject((long) 1,"Process");
        List<IssueSetting>typeList=issueSettingService.getIsssueSettingByTypeAndProject((long) 1,"type");
        model.addAttribute("listclass",listClass);
        model.addAttribute("projectList",projectList);
        model.addAttribute("milestoneList",milestoneList);
        model.addAttribute("statusList",statusList);
        model.addAttribute("processList",processList);
        model.addAttribute("typeList",typeList);
        model.addAttribute("issue", new Issue());
        return "Issue/newissue";
    }
    @PostMapping("/student/add")
    public String addIssue(Model model,@ModelAttribute Issue issue){
        issueService.addIssue(issue);

        return "redirect:/issue/student";
    }
    @PostMapping("/student/update")
    public String update(@ModelAttribute Issue issue){
        issueService.addIssue(issue);

        return "redirect:/issue/student";
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model,HttpSession session, HttpServletRequest request){
        session=request.getSession();
        String email=session.getAttribute("USERNAME").toString();
        User user = userService.getUserByEmail(email);
        model.addAttribute("assignerissue",user);
        List<StudentProject> studentProjectList = studentProjectService.getAllByStudent(user);
        model.addAttribute("studentProjectList",studentProjectList);
        long count= issueService.countIssueByStudent(user.getId());
        int count_assigner=issueService.countByAssinger(user.getId()).size();
        int count_assignee=issueService.countByAssingee(user.getId()).size();
        int count_project=studentProjectList.size();
        model.addAttribute("count",count);
        model.addAttribute("count_assigner",count_assigner);
        model.addAttribute("count_assignee",count_assignee);
        model.addAttribute("count_project",count_project);
        return "Issue/issuedashboard";
    }
    @ResponseBody
    @GetMapping("/pieChart/{assigneeId}")
    public ResponseEntity<PieChartData> getClassIssueStatistics(@PathVariable Long assigneeId) {
        PieChartData pieChartData = issueService.generateClassIssueStatistics(assigneeId);
        return new ResponseEntity<>(pieChartData, HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/pieChart/{assigneeId}/project/{projectId}")
    public ResponseEntity<PieChartData> getClassIssueStatistics(@PathVariable Long assigneeId,@PathVariable Long projectId) {
        PieChartData pieChartData = issueService.countIssueMilestoneByStudentAndProject(assigneeId,projectId);
        return new ResponseEntity<>(pieChartData, HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/pieChart/{assigneeId}/project/{projectId}/milestone")
    public ResponseEntity<PieChartData> getClassIssueStatisticsMilestone(@PathVariable Long assigneeId,@PathVariable Long projectId) {
        PieChartData pieChartData = issueService.countIssueWorkProcessByStudentAndProject(assigneeId,projectId);
        return new ResponseEntity<>(pieChartData, HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/pieChart/{assigneeId}/project/{projectId}/status")
    public ResponseEntity<PieChartData> getClassIssueStatisticsStatus(@PathVariable Long assigneeId,@PathVariable Long projectId) {
        PieChartData pieChartData = issueService.countIssueStatusByStudentAndProject(assigneeId,projectId);
        return new ResponseEntity<>(pieChartData, HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/pieChart/{assigneeId}/project/{projectId}/type")
    public ResponseEntity<PieChartData> getClassIssueStatisticsType(@PathVariable Long assigneeId,@PathVariable Long projectId) {
        PieChartData pieChartData = issueService.countIssueTypeByStudentAndProject(assigneeId,projectId);
        return new ResponseEntity<>(pieChartData, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public String getIssue(Model model, @PathVariable int id, HttpSession session, HttpServletRequest request){
        session=request.getSession();
        String email=session.getAttribute("USERNAME").toString();
        User user = userService.getUserByEmail(email);
        Issue issue= issueService.getIssueById((int)id);
        model.addAttribute("issue",issue);
        List<StudentClass> listClass = studentClassService.getClassesByUser(user);
        model.addAttribute("listclass",listClass);
        return "Issue/issuedetail";
    }



}
