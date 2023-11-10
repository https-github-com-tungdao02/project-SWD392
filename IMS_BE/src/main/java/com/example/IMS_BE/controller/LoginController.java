package com.example.IMS_BE.controller;

import com.example.IMS_BE.entity.Setting;
import com.example.IMS_BE.entity.User;
import com.example.IMS_BE.repository.SettingRepository;
import com.example.IMS_BE.repository.UserRepository;
import com.example.IMS_BE.service.SettingService;
import com.example.IMS_BE.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Autowired
    private SettingService settingService;


    @RequestMapping("/login")
    public String login(){
        return "Common/login";
    }
    @PostMapping("checklogin")
    public String checkLogin(ModelMap model, @RequestParam("username")String username, @RequestParam("password")String password,
                             HttpSession session, HttpServletRequest request ){
        session = request.getSession();
        if(userService.checkLogin(username,password)){
            System.out.println("Login thanh cong");
            if(userService.getRolesByUserName(username).equalsIgnoreCase("admin")) {
                session.setAttribute("USERNAME", username);
                User user = userService.getUserByEmail(username);
                List<Setting> settings = settingService.getAllSettings();
                model.addAttribute("settings", settings);
                return "Common/adminhome";
            }else if (userService.getRolesByUserName(username).equalsIgnoreCase("student")){
                User user = userService.getUserByEmail(username);
                System.out.println(user);
                session.setAttribute("USERNAME", username);
                return "redirect:/issue/student";
            }else if (userService.getRolesByUserName(username).equalsIgnoreCase("teamlead")){
                User user = userService.getUserByEmail(username);
                System.out.println(user);
                session.setAttribute("USERNAME", username);
                return "redirect:/issue/student"; //ae tu return ve trang cua minh
            }else if (userService.getRolesByUserName(username).equalsIgnoreCase("teacher")){
                User user = userService.getUserByEmail(username);
                System.out.println(user);
                session.setAttribute("USERNAME", username);
                return "redirect:/issue/";//ae tu return ve trang cua minh
            }else if (userService.getRolesByUserName(username).equalsIgnoreCase("manager")){
                User user = userService.getUserByEmail(username);
                System.out.println(user);
                session.setAttribute("USERNAME", username);
                return "redirect:/classes/classList";
            }
        }else{
            System.out.println("Login that bai");
            model.addAttribute("ERROR","Username or password not exist!!");
        }
        return "login";
    }

    @RequestMapping("admin_home")
    public String list(ModelMap modelMap, HttpSession session){
        if(session.getAttribute("USERNAME")!=null){
            modelMap.addAttribute("USERNAME","");
            String username = (String) session.getAttribute("USERNAME");
            boolean isAdmin = userService.isAdmin(username);

            if (isAdmin) {
                List<Setting> settings = settingService.getAllSettings();
                modelMap.addAttribute("settings", settings);
                return "Common/adminhome";
            }
        }
        return "Common/accessdenied";
    }



    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "Common/register";
    }


    @PostMapping("checkregister")
    public String checkregister(ModelMap model, @RequestParam("username") String username,
                                @RequestParam("email") String email, @RequestParam("phone") String phone,
                                @RequestParam("password") String password, @RequestParam("re_password") String re_password) {

        if (!password.equals(re_password)) {
            model.addAttribute("ERROR", "Re_password incorrect");
            return "Common/register";
        }
        try {
            userService.registerUser(username, email, phone, password);
            model.addAttribute("SUCCESS", "Đăng ký thành công, quay lại đăng nhập !!");
            return "Common/register";
        } catch (Exception e) {
            model.addAttribute("ERROR", "The user exist!!");
            return "Common/register";
        }
    }



}
