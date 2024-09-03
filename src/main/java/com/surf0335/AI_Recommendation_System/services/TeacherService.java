package com.surf0335.AI_Recommendation_System.services;

import com.surf0335.AI_Recommendation_System.model.Teacher;
import com.surf0335.AI_Recommendation_System.model.TeacherPreference;
import com.surf0335.AI_Recommendation_System.model.Student;
import com.surf0335.AI_Recommendation_System.model.StudentPreference;
import com.surf0335.AI_Recommendation_System.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.surf0335.AI_Recommendation_System.services.TeacherPreferenceService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.surf0335.AI_Recommendation_System.repository.StudentRepo;
@Service
public class TeacherService {

    @Autowired
    private TeacherPreferenceService preferenceService;
    @Autowired
    private TeacherRepo teacherRepository;

    @Autowired
    private StudentRepo studentRepository;

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

    public List<Teacher> getTeachersByStudentPreferences(int studentId) {
        List<StudentPreference> preferences = studentPreferenceService.getPreferencesForStudent(studentId);
        List<Teacher> teachers = getAllTeachers();

        for (Teacher teacher : teachers) {
            double score = 0.0;
            for (StudentPreference preference : preferences) {
                if (teacher.getDescription() != null && containsIgnoreCase(teacher.getDescription(), preference.getResearchField())) {
                    score += preference.getWeight();
                }
            }
            teacher.setScore(score); // 设置教师分数
        }

        // 按分数排序教师
        return teachers.stream()
                .sorted((t1, t2) -> Double.compare(t2.getScore(), t1.getScore())) // 降序排列
                .collect(Collectors.toList());
    }

    // // 自定义的方法，用于不区分大小写的字符串匹配
    // private boolean containsIgnoreCase(String str, String searchStr) {
    //     if (str == null || searchStr == null) {
    //         return false;
    //     }
    //     return str.toLowerCase().contains(searchStr.toLowerCase());
    // }
    
    public List<Teacher> scoreAndSortTeachersByPreferences(List<Teacher> teachers, int studentId) {
        List<StudentPreference> preferences = studentPreferenceService.getPreferencesForStudent(studentId);

        for (Teacher teacher : teachers) {
            double score = 0.0;
            for (StudentPreference preference : preferences) {
                if (teacher.getDescription() != null && containsIgnoreCase(teacher.getDescription(), preference.getResearchField())) {
                    score += preference.getWeight();
                }
            }
            teacher.setScore(score); // 设置教师分数
        }

        // 按分数排序教师
        return teachers.stream()
                .sorted((t1, t2) -> Double.compare(t2.getScore(), t1.getScore())) // 降序排列
                .collect(Collectors.toList());
    }

    // 自定义的方法，用于不区分大小写的字符串匹配
    private boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.toLowerCase().contains(searchStr.toLowerCase());
    }

    public List<Student> getStudentsByTeacherPreferences(int teacherId, List<Student> students) {
        List<TeacherPreference> preferences = preferenceService.getPreferencesForTeacher(teacherId);

        for (Student student : students) {
            double score = 0.0;

            for (TeacherPreference preference : preferences) {
                if (student.getDescription() != null && containsIgnoreCase(student.getDescription(), preference.getRequirement())) {
                    score += preference.getWeight();
                }
            }
            student.setScore(score);
        }

        return students.stream()
                .sorted((s1, s2) -> Double.compare(s2.getScore(), s1.getScore()))
                .collect(Collectors.toList());
    }
    
}

