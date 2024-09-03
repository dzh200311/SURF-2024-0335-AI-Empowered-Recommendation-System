package com.surf0335.AI_Recommendation_System.services;

import com.surf0335.AI_Recommendation_System.model.TeacherPreferenceList;
import com.surf0335.AI_Recommendation_System.repository.TeacherPreferenceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherPreferenceListService {

    @Autowired
    private TeacherPreferenceListRepository preferenceListRepository;

    // 获取某个教师的偏好列表，如果没有找到则返回 null
    public TeacherPreferenceList getPreferenceListByTeacherId(int teacherId) {
        return preferenceListRepository.findByTeacherId(teacherId);
    }

    // 保存偏好列表
    public TeacherPreferenceList savePreferenceList(TeacherPreferenceList preferenceList) {
        return preferenceListRepository.save(preferenceList);
    }
}
