package com.example.IMS_BE.controller;

import com.example.IMS_BE.entity.*;
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
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassesServiceImpl classService;

    @Autowired
    private StudentProjectService studentProjectService;

    @GetMapping("/")
    public String index(@RequestParam(value = "id", required = false, defaultValue = "") String id, Model model,@RequestParam(name = "pageNo",defaultValue = "1")Integer pageNo) {
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
       // Page<StudentProject> studentProjects = studentProjectService.getAl(pageNo);
        model.addAttribute("studentProjects", studentProjects);
        model.addAttribute("lstClass", classes);
        model.addAttribute("lstUser", users);
        model.addAttribute("lstProject", projects);

        model.addAttribute("projectForm", formModel);
        model.addAttribute("studentProjectForm", formModel2);


        return "Project/projectmember";
    }

    @PostMapping("/saveProject")
    public String save(@ModelAttribute("projectForm") Project project) {
        projectServiceImpl.saveProject(project);
        return "redirect:/classes/edit/" + project.getClasses().getId();
    }

    @PostMapping("/saveStudentProject")
    public String saveStudentProject(@ModelAttribute("studentProjectForm") StudentProject project2) {
        studentProjectService.saveStudentProject(project2);
        return "redirect:/projectmember/";
    }

    @ResponseBody
    @GetMapping("/api/getproject/{id}")
    public ResponseEntity<List<Project>> get(@PathVariable Long id){
        return new ResponseEntity<List<Project>> (classService.findProjectByClassId(id), HttpStatus.OK);
    }

    @GetMapping("/editProject/{id}/{classid}")
    public String editProject(@PathVariable Long id,@PathVariable Long classid, Model model) {
        Project project = projectServiceImpl.getProjectById(id);

        List<Classes> classes = classService.GetAllClasses();
        List<StudentProject> studentProjects = studentProjectService.getAllStudentProjects();
        List<User> students = classService.findUsersByClassId(classid);

        model.addAttribute("member", studentProjects);

        model.addAttribute("project", project);
        model.addAttribute("lstClass", classes);
        model.addAttribute("lstUser", students);

        return "Project/editproject";
    }


    @RequestMapping("/removeMember/{projectId}")
    public String viewStudentsInProject(@PathVariable Long projectId, Model model) {
         List<StudentProject> studentsInProject = studentProjectService.getStudentsByProjectId(projectId);
       // StudentProject studentsInProject = studentProjectService.getStudentProjectById(projectId);

        List<Project> projects = projectServiceImpl.getAllProjects();
        model.addAttribute("lstProject", projects);


        model.addAttribute("studentsInProject", studentsInProject);
        List<StudentProject> studentProjects = studentProjectService.getAllStudentProjects();

        model.addAttribute("project", studentProjects);

        return "Project/updatemember";
    }


    @RequestMapping("/addnewproject/{classId}")
    public String addnewproject(@PathVariable Long classId, Model model) {
        Project project = projectServiceImpl.getProjectById(classId);
        List<User> students = classService.findUsersByClassId(classId);
        List<Project> projects = projectServiceImpl.getAllProjects();


        model.addAttribute("addprojectForm", project);

        model.addAttribute("Studentproject", students);

        model.addAttribute("lstProject", projects);

        return "Project/addnewproject";
    }
    @PostMapping("/addNewProject")
    public String addNewProject(@ModelAttribute("addprojectForm") Project project) {
        projectServiceImpl.saveProject(project);
        return "redirect:/classes/edit/" + project.getClasses().getId();
    }


    @PostMapping("/addStudentProject")
    public String addStudentProject(@ModelAttribute("addprojectForm") StudentProject project2) {
        studentProjectService.saveStudentProject(project2);
        return "redirect:/projectmember/";
    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentProjectService.deleteStudentProject(id);
        return "redirect:/projectmember/";
    }

    @GetMapping("/deleteproject/{id}")
    public String deleteproject(@PathVariable Long id) {
        projectServiceImpl.deleteProject(id);
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
        return "redirect:/projectmember/removeMember/" + projectId;
    }

    @RequestMapping("/moveMember/{memberId}")
    public String moveMember(@PathVariable Long memberId, Model model) {

        List<Project> projects = projectServiceImpl.getAllProjects();
        model.addAttribute("lstProject", projects);

        StudentProject studentsInProject = studentProjectService.getStudentProjectById(memberId);

        model.addAttribute("member", studentsInProject);

        return "Project/movemember";
    }

    @PostMapping("/moveStudentFromProject")
    public String moveStudentFromProject(@ModelAttribute("moveStudentFromProject") StudentProject project) {
        studentProjectService.moveStudentBetweenProjects(project);
        return "redirect:/projectmember/removeMember/" + project.getProject().getId();
    }


}