package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.StudentPreferenceList;
import com.surf0335.AI_Recommendation_System.services.StudentPreferenceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/preference-lists")
public class StudentPreferencelistController {

    @Autowired
    private StudentPreferenceListService preferenceListService;

    // 获取某个学生的偏好列表
    @GetMapping("/list")
    public String listPreferencesByStudent(@RequestParam("studentId") int studentId, Model model) {
        StudentPreferenceList preferenceList = preferenceListService.getPreferenceListByStudentId(studentId);
        model.addAttribute("preferenceList", preferenceList);
        return "preference-lists/list-preferences";
    }

    // 保存偏好列表
    @PostMapping("/save")
    public String savePreferenceList(@ModelAttribute("preferenceList") StudentPreferenceList preferenceList) {
        preferenceListService.savePreferenceList(preferenceList);
        return "redirect:/preference-lists/list?studentId=" + preferenceList.getStudent().getId();
    }
}
