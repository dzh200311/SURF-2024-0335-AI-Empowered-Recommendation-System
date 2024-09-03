package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.StudentPreference;
import com.surf0335.AI_Recommendation_System.model.User;
import com.surf0335.AI_Recommendation_System.services.StudentPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/preferences")
public class StudentPreferenceController {

    @Autowired
    private StudentPreferenceService preferenceService;

    // 获取某个学生的偏好列表
    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<StudentPreference>> listPreferencesByStudent(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int studentId = user != null && user.getStudent() != null ? user.getStudent().getId() : -1;

        if (studentId == -1) {
            // 处理没有找到学生的情况
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            // 获取学生的偏好列表
            List<StudentPreference> preferences = preferenceService.getPreferencesForStudent(studentId);
            return ResponseEntity.ok(preferences);
        } catch (Exception e) {
            // 记录错误日志
            System.err.println("Error loading preferences for studentId: " + studentId);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 更新偏好
    @PutMapping("/{id}")
    public ResponseEntity<StudentPreference> updatePreference(@PathVariable Long id, @RequestBody StudentPreference studentPreference) {
        try {
            StudentPreference updatedPreference = preferenceService.updatePreference(id, studentPreference);
            return ResponseEntity.ok(updatedPreference);
        } catch (Exception e) {
            System.err.println("Error updating preference with id: " + id);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 根据偏好ID获取偏好详情
    @GetMapping("/{preferenceId}")
    @ResponseBody
    public ResponseEntity<StudentPreference> getPreferenceById(@PathVariable("preferenceId") int preferenceId) {
        try {
            // 获取指定ID的偏好
            StudentPreference preference = preferenceService.getPreferenceById(preferenceId);
            return ResponseEntity.ok(preference);
        } catch (Exception e) {
            // 记录错误日志
            System.err.println("Error retrieving preference for preferenceId: " + preferenceId);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<String> handleConversionException(HttpMessageConversionException ex) {
        // 记录详细错误信息
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data format");
    }
}

    // 保存单个偏好
    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StudentPreference> savePreference(@RequestBody StudentPreference preference, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int studentId = user != null && user.getStudent() != null ? user.getStudent().getId() : -1;

        if (studentId == -1) {
            // 处理没有找到学生的情况
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            // 将偏好保存到偏好列表中
            StudentPreference savedPreference = preferenceService.savePreference(preference, studentId);
            return ResponseEntity.ok(savedPreference);
        } catch (IllegalStateException e) {
            // 处理添加偏好时的非法状态异常，例如超过3个偏好的限制
            System.err.println("Error saving preference: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            // 记录其他错误日志
            System.err.println("Error saving preference for studentId: " + studentId);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 发布所有偏好，将其关联到 `StudentPreferenceList`
    @PostMapping("/publish")
    @ResponseBody
    public ResponseEntity<Map<String, String>> publishPreferences(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int studentId = user != null && user.getStudent() != null ? user.getStudent().getId() : -1;

        if (studentId == -1) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Student not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Map<String, String> response = new HashMap<>();
        try {
            boolean isPublished = preferenceService.isPreferencesPublished(studentId);
            if (isPublished) {
                response.put("status", "fail");
                response.put("message", "Preferences are already published!");
            } else {
                preferenceService.publishPreferences(studentId);
                response.put("status", "success");
                response.put("message", "Preferences published successfully!");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 记录错误日志
            System.err.println("Error publishing preferences for studentId: " + studentId);
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "Error publishing preferences.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 检查偏好是否已发布
    @GetMapping("/isPublished")
    @ResponseBody
    public ResponseEntity<Boolean> isPublished(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int studentId = user != null && user.getStudent() != null ? user.getStudent().getId() : -1;

        if (studentId == -1) {
            // 处理没有找到学生的情况
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

        try {
            boolean isPublished = preferenceService.isPreferencesPublished(studentId);
            return ResponseEntity.ok(isPublished);
        } catch (Exception e) {
            // 记录错误日志
            System.err.println("Error checking published status for studentId: " + studentId);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @DeleteMapping("/{preferenceId}")
    @ResponseBody
    public ResponseEntity<String> deletePreference(@PathVariable("preferenceId") int preferenceId) {
        System.out.println("Attempting to delete preference with ID: " + preferenceId);

        try {
            // 首先解除外键引用，将 student_preference_list 表中引用该 preferenceId 的字段设置为 NULL
            preferenceService.nullifyForeignKeyReferences(preferenceId);
            // 然后删除 preference
            preferenceService.deletePreference(preferenceId);
            return ResponseEntity.ok("Preference deleted successfully!");
        } catch (Exception e) {
            System.err.println("Error deleting preference for preferenceId: " + preferenceId);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting preference.");
        }
    }
}
