package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.TeacherPreferenceDTO;
import com.surf0335.AI_Recommendation_System.model.TeacherPreference;
import com.surf0335.AI_Recommendation_System.model.User;
import com.surf0335.AI_Recommendation_System.services.TeacherPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.surf0335.AI_Recommendation_System.services.TeacherService;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher-preferences")
public class TeacherPreferenceController {
@Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherPreferenceService preferenceService;

    // 获取某个教师的偏好列表
    @GetMapping("/list")
    @ResponseBody
    public List<TeacherPreferenceDTO> listPreferencesByTeacher(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getTeacher() == null) {
            return new ArrayList<>(); // 返回空列表
        }

        try {
            int teacherId = user.getTeacher().getId();
            List<TeacherPreference> preferences = preferenceService.getPreferencesForTeacher(teacherId);
            return convertToDTO(preferences); // 转换为DTO列表
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // 返回空列表
        }
    }

  
    
    @PutMapping("/{id}")
    public ResponseEntity<TeacherPreferenceDTO> updatePreference(@PathVariable Long id, @RequestBody TeacherPreferenceDTO teacherPreferenceDTO, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null && user.getTeacher() != null) {
            // 通过ID获取现有的TeacherPreference
            TeacherPreference existingPreference = preferenceService.getPreferenceById(id.intValue());

            
            if (existingPreference == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 如果找不到对应的Preference
            }
    
            // 将DTO的值更新到现有的实体中
            existingPreference.setRequirement(teacherPreferenceDTO.getRequirement());
            existingPreference.setWeight(teacherPreferenceDTO.getWeight());
            existingPreference.setDescription(teacherPreferenceDTO.getDescription());
            existingPreference.setOrderIndex(teacherPreferenceDTO.getOrderIndex());
    
            // 保留与TeacherPreferenceList的关联
            existingPreference.setTeacherPreferenceList(existingPreference.getTeacherPreferenceList());
    
            // 保留与Teacher的关联
            existingPreference.setTeacher(existingPreference.getTeacher());
    
            // 更新实体
            TeacherPreference updatedPreference = preferenceService.updatePreference(id, existingPreference);
            return ResponseEntity.ok(convertToDTO(updatedPreference));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
    

    // 根据偏好ID获取偏好详情
    @GetMapping("/{preferenceId}")
    @ResponseBody
    public TeacherPreferenceDTO getPreferenceById(@PathVariable("preferenceId") int preferenceId, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            if (user != null && user.getTeacher() != null) {
                TeacherPreference preference = preferenceService.getPreferenceById(preferenceId);
                return convertToDTO(preference);
            } else {
                throw new IllegalStateException("User is not logged in or not a teacher.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 返回空指针以表示失败
        }
    }

    // 保存偏好
    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<TeacherPreferenceDTO> savePreference(@RequestBody TeacherPreferenceDTO preferenceDTO, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int teacherId = user != null && user.getTeacher() != null ? user.getTeacher().getId() : -1;

        if (teacherId == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            TeacherPreference preference = convertToEntity(preferenceDTO);
            TeacherPreference savedPreference = preferenceService.savePreference(preference, teacherId);
            return ResponseEntity.ok(convertToDTO(savedPreference));
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 发布所有偏好，将其关联到 `TeacherPreferenceList`
    @PostMapping("/publish")
    @ResponseBody
    public ResponseEntity<Map<String, String>> publishPreferences(HttpSession session) {
        Map<String, String> response = new HashMap<>();
        try {
            User user = (User) session.getAttribute("user");
            if (user != null && user.getTeacher() != null) {
                int teacherId = user.getTeacher().getId();
                boolean isPublished = preferenceService.isPreferencesPublished(teacherId);
                if (isPublished) {
                    response.put("status", "fail");
                    response.put("message", "Preferences are already published!");
                } else {
                    preferenceService.publishPreferences(teacherId);
                    response.put("status", "success");
                    response.put("message", "Preferences published successfully!");
                }
                return ResponseEntity.ok(response);
            } else {
                throw new IllegalStateException("User is not logged in or not a teacher.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "Error publishing preferences.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{preferenceId}")
    @ResponseBody
    public ResponseEntity<String> deletePreference(@PathVariable("preferenceId") int preferenceId, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            if (user != null && user.getTeacher() != null) {
                preferenceService.nullifyForeignKeyReferences(preferenceId);
                preferenceService.deletePreference(preferenceId);
                return ResponseEntity.ok("Preference deleted successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not logged in or not a teacher.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting preference.");
        }
    }

    // 辅助方法：实体类转换为DTO
    private TeacherPreferenceDTO convertToDTO(TeacherPreference preference) {
        TeacherPreferenceDTO dto = new TeacherPreferenceDTO();
        dto.setId(preference.getId());
        dto.setRequirement(preference.getRequirement());
        dto.setWeight(preference.getWeight());
        dto.setDescription(preference.getDescription());
        dto.setOrderIndex(preference.getOrderIndex());
        return dto;
    }

    // 辅助方法：DTO转换为实体类
    private TeacherPreference convertToEntity(TeacherPreferenceDTO dto) {
        TeacherPreference preference = new TeacherPreference();
        preference.setId(dto.getId());
        preference.setRequirement(dto.getRequirement());
        preference.setWeight(dto.getWeight());
        preference.setDescription(dto.getDescription());
        preference.setOrderIndex(dto.getOrderIndex());
        return preference;
    }

    // 辅助方法：将实体类列表转换为DTO列表
    private List<TeacherPreferenceDTO> convertToDTO(List<TeacherPreference> preferences) {
        List<TeacherPreferenceDTO> dtoList = new ArrayList<>();
        for (TeacherPreference preference : preferences) {
            dtoList.add(convertToDTO(preference));
        }
        return dtoList;
    }
}
