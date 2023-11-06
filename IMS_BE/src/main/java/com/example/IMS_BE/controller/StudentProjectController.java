package com.example.IMS_BE.controller;

import com.example.IMS_BE.entity.Classes;
import com.example.IMS_BE.entity.Project;
import com.example.IMS_BE.entity.StudentProject;
import com.example.IMS_BE.entity.User;
import com.example.IMS_BE.service.UserService;
import com.example.IMS_BE.service.impl.ClassesServiceImpl;
import com.example.IMS_BE.service.impl.ProjectServiceImpl;
import com.example.IMS_BE.service.impl.StudentProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projectmember")
public class StudentProjectController {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassesServiceImpl classService;


    @Autowired
    private StudentProjectService studentProjectService;

    @GetMapping("/")
    public String index(@RequestParam(value = "id", required = false, defaultValue = "") String id, Model model) {
        Project formModel = Project.builder().build();

        if (!id.isEmpty()) {
            Long projectId = Long.valueOf(id);
            formModel = projectServiceImpl.getProjectById(projectId);
        }

         StudentProject formModel2 = StudentProject.builder().build();

        if (!id.isEmpty()) {
            Long projectId = Long.valueOf(id);
            formModel2 = studentProjectService.getStudentProjectById(projectId);
        }


        List<Classes> classes = classService.GetAllClasses();
        List<User> users = userService.getAllUsers();
        List<Project> projects = projectServiceImpl.getAllProjects();
        List<StudentProject> studentProjects = studentProjectService.getAllStudentProjects();
// List<StudentProject> studentProjects2 = studentProjectService.searchAll();

        model.addAttribute("studentProjects", studentProjects);
     //   model.addAttribute("studentProjects2", studentProjects2);
        model.addAttribute("lstClass", classes);
        model.addAttribute("lstUser", users);
        model.addAttribute("lstProject", projects);

        model.addAttribute("projectForm", formModel);
        model.addAttribute("studentProjectForm", formModel2);

        return "projectmember";
    }

    @PostMapping("/saveProject")
    public String save(@ModelAttribute("projectForm") Project project) {
        projectServiceImpl.saveProject(project);
        return "redirect:/projectmember/";
    }

    @PostMapping("/saveStudentProject")
    public String saveStudentProject(@ModelAttribute("studentProjectForm") StudentProject project2) {
        studentProjectService.saveStudentProject(project2);
        return "redirect:/projectmember/";
    }

    @ResponseBody
    @GetMapping("/api/getproject/{id}")
    public ResponseEntity<StudentProject> get(@PathVariable Long id){
        return new ResponseEntity<StudentProject> (studentProjectService.getStudentProjectById(id), HttpStatus.OK);
    }

    @GetMapping("/editProject/{id}")
    public String editProject(@PathVariable Long id, Model model) {
        Project project = projectServiceImpl.getProjectById(id);
        List<Classes> classes = classService.GetAllClasses();
        List<User> users = userService.getAllUsers();
        List<StudentProject> studentProjects = studentProjectService.getAllStudentProjects();


        model.addAttribute("member", studentProjects);

        model.addAttribute("project", project);
        model.addAttribute("lstClass", classes);
        model.addAttribute("lstUser", users);

        return "editproject";
    }


    @RequestMapping("/removeMember/{projectId}")
    public String viewStudentsInProject(@PathVariable Long projectId, Model model) {
         List<StudentProject> studentsInProject = studentProjectService.getStudentsByProjectId(projectId);
       // StudentProject studentsInProject = studentProjectService.getStudentProjectById(projectId);
        model.addAttribute("studentsInProject", studentsInProject);
        return "updatemember";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentProjectService.deleteStudentProject(id);
        return "redirect:/projectmember/";
    }

    @GetMapping("/deleteStudent/{projectId}")
    public String deleteStudent(@PathVariable Long projectId, @RequestParam Long userId) {
        studentProjectService.deleteStudentByProjectAndUser(projectId, userId);
        return "redirect:/projectmember/";
    }

    @PostMapping("/removeStudentFromProject")
    public String removeStudentFromProject(@RequestParam("projectId") Long projectId, @RequestParam("studentId") Long studentId) {
        studentProjectService.removeStudentFromProject(projectId, studentId);
        return "redirect:/projectmember/";
    }




}