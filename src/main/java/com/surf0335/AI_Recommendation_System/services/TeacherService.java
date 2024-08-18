package com.surf0335.AI_Recommendation_System.services;

import com.surf0335.AI_Recommendation_System.model.Teacher;
import com.surf0335.AI_Recommendation_System.model.StudentPreference;
import com.surf0335.AI_Recommendation_System.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepository;

    @Autowired
    private StudentPreferenceService studentPreferenceService;  // 正确注入 StudentPreferenceService

    // 获取所有老师
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // 根据ID获取老师
    public Teacher getTeacherById(int id) {
        return teacherRepository.findById(id).orElse(null);
    }

    // 保存老师
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // 根据ID更新老师
    public Teacher updateTeacher(int id, Teacher updatedTeacher) {
        updatedTeacher.setId(id);
        return teacherRepository.save(updatedTeacher);
    }

    // 根据ID删除老师
    public void deleteTeacher(int id) {
        teacherRepository.deleteById(id);
    }

    // 根据学生的偏好获取匹配的老师，并按权重打分
    public List<Teacher> getTeachersByStudentPreferences(int studentId) {
        // 获取学生的偏好列表
        List<StudentPreference> preferences = studentPreferenceService.getPreferencesForStudent(studentId);
        
        // 获取所有老师
        List<Teacher> teachers = getAllTeachers();

        // 按照匹配偏好进行打分
        Map<Teacher, Double> teacherScores = new HashMap<>();
        for (Teacher teacher : teachers) {
            double score = 0.0;
            for (StudentPreference preference : preferences) {
                if (teacher.getDescription() != null && teacher.getDescription().contains(preference.getResearchField())) {
                    score += preference.getWeight();
                }
            }
            teacherScores.put(teacher, score);
        }

        // 根据分数排序老师
        return teacherScores.entrySet().stream()
                .sorted(Map.Entry.<Teacher, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
