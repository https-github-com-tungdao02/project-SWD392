package com.example.IMS_BE.controller;

import com.example.IMS_BE.entity.Setting;
import com.example.IMS_BE.repository.SettingRepository;
import com.example.IMS_BE.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SettingController {

    @Autowired
    private SettingRepository settingRepository;

    @Autowired
    SettingService settingService;

    @GetMapping("/edit/setting/{id}")
    public String editSettingForm(@PathVariable Long id, Model model) {
        Setting setting = settingService.getSettingById(id);
        if (setting != null) {
            model.addAttribute("setting", setting);
            List<String> typeOptions = settingService.getAllTypeOptions();
            model.addAttribute("typeOptions", typeOptions);
            List<Setting> settingsList = settingService.getAllSettings();
            model.addAttribute("settings", settingsList);
            return "Common/editsetting";
        } else {

            return "redirect:/admin_home";
        }
    }

    @PostMapping("/edit/setting/{id}")
    public String editSetting(@PathVariable Long id, @ModelAttribute("setting") Setting setting) {
        Setting existingSetting = settingService.getSettingById(id);
        if (existingSetting != null) {

            existingSetting.setName(setting.getName());
            existingSetting.setType(setting.getType());
            settingService.saveSetting(existingSetting);
        }
        return "redirect:/admin_home";
    }


    @PostMapping("/delete/setting/{id}")
    public String deleteSetting(@PathVariable Long id) {
        settingService.deleteSetting(id);
        return "redirect:/admin_home";
    }

    @GetMapping("/add-new")
    public String addNewSettingForm(Model model) {
        model.addAttribute("setting", new Setting());
        List<String> settingList = settingService.getAllTypes();
        model.addAttribute("typeList", settingList);
        return "Common/addsetting";
    }

    @PostMapping("/add-new")
    public String addNewSetting(@ModelAttribute Setting setting) {
        settingService.saveSetting(setting);
        return "redirect:/admin_home";
    }

    @PostMapping("/delete-selected-settings")
    public String deleteSelectedSettings(@RequestParam("selectedIds") List<Long> selectedIds) {
        settingService.deleteSelectedSettings(selectedIds);
        return "redirect:/admin_home";
    }

}