package com.surf0335.AI_Recommendation_System.services;

import com.surf0335.AI_Recommendation_System.model.Student;
import com.surf0335.AI_Recommendation_System.model.StudentPreference;
import com.surf0335.AI_Recommendation_System.model.StudentPreferenceList;
import com.surf0335.AI_Recommendation_System.repository.StudentPreferenceListRepository;
import com.surf0335.AI_Recommendation_System.repository.StudentPreferenceRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentPreferenceService {

    @Autowired
    private StudentPreferenceRepository preferenceRepository;

    @Autowired
    private StudentPreferenceListRepository preferenceListRepository;

    // 保存单个偏好
    public StudentPreference savePreference(StudentPreference preference, int studentId) {
        // 查找现有的 StudentPreferenceList
        StudentPreferenceList preferenceList = preferenceListRepository.findByStudentId(studentId);

        if (preferenceList == null) {
            // 如果不存在，创建一个新的列表
            preferenceList = new StudentPreferenceList();
            Student student = new Student();
            student.setId(studentId);
            preferenceList.setStudent(student);
        }

        // 添加新的偏好到偏好列表中
        preferenceList.addPreference(preference);

        // 保存偏好列表，这会级联保存新添加的偏好
        preferenceListRepository.save(preferenceList);

        // 返回保存的偏好
        return preference;
    }

    public StudentPreference updatePreference(Long id, StudentPreference studentPreference) {
        // 更新偏好的逻辑
        studentPreference.setId(id);
        return preferenceRepository.save(studentPreference);
    }

    public StudentPreference getPreferenceById(int preferenceId) {
        // 从数据库中获取指定ID的偏好
        return preferenceRepository.findById(preferenceId).orElse(null);
    }

    // 获取某个学生的所有偏好
    public List<StudentPreference> getPreferencesForStudent(int studentId) {
        StudentPreferenceList preferenceList = preferenceListRepository.findByStudentId(studentId);
        if (preferenceList != null) {
            return preferenceList.getPreferences();
        } else {
            return List.of(); // 返回一个空列表
        }
    }

   // 发布所有偏好，将其关联到 StudentPreferenceList 的三个固定字段
public void publishPreferences(int studentId) {
    StudentPreferenceList preferenceList = preferenceListRepository.findByStudentId(studentId);

    if (preferenceList == null || preferenceList.getPreferences().isEmpty()) {
        throw new IllegalArgumentException("No preferences found for student.");
    }

    // 将偏好存储到三个固定字段中
    List<StudentPreference> preferences = preferenceList.getPreferences();
    if (preferences.size() > 0) preferenceList.setPreference1(preferences.get(0));
    if (preferences.size() > 1) preferenceList.setPreference2(preferences.get(1));
    if (preferences.size() > 2) preferenceList.setPreference3(preferences.get(2));

    preferenceList.setPublished(true);  // 设置为已发布
    preferenceListRepository.save(preferenceList);  // 保存偏好列表
}


// 在 StudentPreferenceService 中添加一个方法来检查偏好是否已发布
public boolean isPreferencesPublished(int studentId) {
    StudentPreferenceList preferenceList = preferenceListRepository.findByStudentId(studentId);
    return preferenceList != null && preferenceList.isPublished();
}

    // 删除特定偏好
    public void deletePreference(int preferenceId) {
        StudentPreference preference = preferenceRepository.findById(preferenceId).orElse(null);
        if (preference != null) {
            StudentPreferenceList preferenceList = preference.getStudentPreferenceList();
            if (preferenceList != null) {
                if (preferenceList.getPreference1() != null && preferenceList.getPreference1().getId().equals(preferenceId)) {
                    preferenceList.setPreference1(null);
                }
                if (preferenceList.getPreference2() != null && preferenceList.getPreference2().getId().equals(preferenceId)) {
                    preferenceList.setPreference2(null);
                }
                if (preferenceList.getPreference3() != null && preferenceList.getPreference3().getId().equals(preferenceId)) {
                    preferenceList.setPreference3(null);
                }
                preferenceListRepository.save(preferenceList);
            }
            preferenceRepository.delete(preference);
        }
    }
    @Transactional
    public void nullifyForeignKeyReferences(int preferenceId) {
        // 将所有引用该 preferenceId 的外键字段设置为 NULL
        preferenceListRepository.nullifyPreference1(preferenceId);
        preferenceListRepository.nullifyPreference2(preferenceId);
          preferenceListRepository.nullifyPreference3(preferenceId);
    }

    
}

