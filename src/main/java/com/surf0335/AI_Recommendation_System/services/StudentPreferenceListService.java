package com.surf0335.AI_Recommendation_System.services;

import com.surf0335.AI_Recommendation_System.model.StudentPreferenceList;
import com.surf0335.AI_Recommendation_System.repository.StudentPreferenceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentPreferenceListService {

    @Autowired
    private StudentPreferenceListRepository preferenceListRepository;

    // 获取某个学生的偏好列表，如果没有找到则返回 null
    public StudentPreferenceList getPreferenceListByStudentId(int studentId) {
        return preferenceListRepository.findByStudentId(studentId);
    }

    // 保存偏好列表
    public StudentPreferenceList savePreferenceList(StudentPreferenceList preferenceList) {
        return preferenceListRepository.save(preferenceList);
    }
}
