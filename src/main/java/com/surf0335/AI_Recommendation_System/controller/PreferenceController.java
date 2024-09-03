package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.Preference;
import com.surf0335.AI_Recommendation_System.services.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences")
public class PreferenceController {

    @Autowired
    private PreferenceService preferenceService;

    // 获取所有偏好
    @GetMapping
    public List<Preference> getAllPreferences() {
        return preferenceService.getAllPreferences();
    }

    // 根据ID获取偏好
    @GetMapping("/{id}")
    public Preference getPreferenceById(@PathVariable Long id) {
        return preferenceService.getPreferenceById(id);
    }

    // 创建偏好，并可选地关联教师和学生
    @PostMapping
    public Preference createPreference(@RequestBody Preference preference, 
                                       @RequestParam(required = false) Integer teacherId,
                                       @RequestParam(required = false) Integer studentId) {
        return preferenceService.createPreference(preference, teacherId, studentId);
    }

    // 更新偏好，并可选地更新关联的教师和学生
    @PutMapping("/{id}")
    public Preference updatePreference(@PathVariable Long id, 
                                       @RequestBody Preference preference, 
                                       @RequestParam(required = false) Integer teacherId,
                                       @RequestParam(required = false) Integer studentId) {
        return preferenceService.updatePreference(id, preference, teacherId, studentId);
    }

    // 删除偏好
    @DeleteMapping("/{id}")
    public void deletePreference(@PathVariable Long id) {
        preferenceService.deletePreference(id);
    }
}
