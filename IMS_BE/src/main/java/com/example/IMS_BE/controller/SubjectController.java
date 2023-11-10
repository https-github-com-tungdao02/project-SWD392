package com.example.IMS_BE.controller;


import com.example.IMS_BE.entity.Subject;
import com.example.IMS_BE.entity.User;
import com.example.IMS_BE.repository.ISubjectRepository;
import com.example.IMS_BE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private ISubjectRepository ISubjectRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String showPaginatedSubjects(Pageable pageable, @RequestParam(required = false) String keyword, @RequestParam(required = false) String searchBy, Model model) {
        List<User> managerList = userService.findManagerList();
        model.addAttribute("managerList",managerList);
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        Page<Subject> page;
        if (keyword != null && searchBy != null) {
            if (searchBy.equals("name")) {
                page = ISubjectRepository.findByNameContaining(keyword, pageable);
            } else if (searchBy.equals("manager")) {
                page = ISubjectRepository.findByManager_UsernameContaining(keyword, pageable);
            } else {
                page = ISubjectRepository.findAll(pageable);
            }
        } else {
            page = ISubjectRepository.findAll(pageable);
        }
        model.addAttribute("page", page);
        return "Subject/subject";
    }

    @GetMapping("/add")
        public String subjectAdd(Model model){
        List<User> managerList = userService.findManagerList();
        model.addAttribute("managerList",managerList);
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "Subject/subject_add";
        }

    @GetMapping("/details")
    public String subjectDetails(){
        return "subject_detail";
    }



    @PostMapping("/add")
    public String saveSubjects(Subject subject, RedirectAttributes redirectAttributes){
        ISubjectRepository.save(subject);
        redirectAttributes.addFlashAttribute("message", "The Subject has been saved successfully!");
        return "redirect:/subject/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteSubject(@RequestParam("id") Long subjectId, Model model) {
        ISubjectRepository.deleteById(subjectId);
        return "redirect:/subject/list";
    }

    @PostMapping("/update")
    public String updateSubject(@RequestBody Subject subject, RedirectAttributes redirectAttributes) {
        ISubjectRepository.save(subject);
        redirectAttributes.addFlashAttribute("message", "The Subject has been updated successfully!");
        return "redirect:/subject/list";
    }


}
