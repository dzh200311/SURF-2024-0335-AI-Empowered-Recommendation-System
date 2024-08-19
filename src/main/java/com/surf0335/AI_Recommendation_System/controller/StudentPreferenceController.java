package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.StudentPreference;
import com.surf0335.AI_Recommendation_System.services.StudentPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public List<StudentPreference> listPreferencesByStudent(@RequestParam(value = "studentId", defaultValue = "1") int studentId) {
        try {
            // 获取学生的偏好列表
            return preferenceService.getPreferencesForStudent(studentId);
        } catch (Exception e) {
            // 记录错误日志
            System.err.println("Error loading preferences for studentId: " + studentId);
            e.printStackTrace();
            return null; // 返回空指针以表示失败
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentPreference> updatePreference(@PathVariable Long id, @RequestBody StudentPreference studentPreference) {
        StudentPreference updatedPreference = preferenceService.updatePreference(id, studentPreference);
        return ResponseEntity.ok(updatedPreference);
    }
    
    // 根据偏好ID获取偏好详情
    @GetMapping("/{preferenceId}")
    @ResponseBody
    public StudentPreference getPreferenceById(@PathVariable("preferenceId") int preferenceId) {
        try {
            // 获取指定ID的偏好
            return preferenceService.getPreferenceById(preferenceId);
        } catch (Exception e) {
            // 记录错误日志
            System.err.println("Error retrieving preference for preferenceId: " + preferenceId);
            e.printStackTrace();
            return null; // 返回空指针以表示失败
        }
    }

    // 保存单个偏好
    @PostMapping("/save")
    @ResponseBody
    public StudentPreference savePreference(@RequestBody StudentPreference preference, @RequestParam(value = "studentId", defaultValue = "1") int studentId) {
        try {
            // 将偏好保存到偏好列表中
            return preferenceService.savePreference(preference, studentId);
        } catch (IllegalStateException e) {
            // 处理添加偏好时的非法状态异常，例如超过3个偏好的限制
            System.err.println("Error saving preference: " + e.getMessage());
            e.printStackTrace();
            return null; // 返回空指针以表示失败
        } catch (Exception e) {
            // 记录其他错误日志
            System.err.println("Error saving preference for studentId: " + studentId);
            e.printStackTrace();
            return null; // 返回空指针以表示失败
        }
    }

    // 发布所有偏好，将其关联到 `StudentPreferenceList`
@PostMapping("/publish")
@ResponseBody
public ResponseEntity<Map<String, String>> publishPreferences(@RequestParam(value = "studentId", defaultValue = "1") int studentId) {
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

// 添加一个用于检查偏好是否已发布的方法
@GetMapping("/isPublished")
@ResponseBody
public boolean isPublished(@RequestParam(value = "studentId", defaultValue = "1") int studentId) {
    try {
        return preferenceService.isPreferencesPublished(studentId);
    } catch (Exception e) {
        // 记录错误日志
        System.err.println("Error checking published status for studentId: " + studentId);
        e.printStackTrace();
        return false; // 如果出错，返回false表示未发布
    }
}


@DeleteMapping("/{preferenceId}")
@ResponseBody
public ResponseEntity<String> deletePreference(@PathVariable("preferenceId") int preferenceId) {
    System.out.println("Attempting to delete preference with ID: " + preferenceId); // 添加日志

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
