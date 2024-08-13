package com.surf0335.AI_Recommendation_System.services;

import com.surf0335.AI_Recommendation_System.model.Preference;
import com.surf0335.AI_Recommendation_System.model.Student;
import com.surf0335.AI_Recommendation_System.model.Teacher;
import com.surf0335.AI_Recommendation_System.repository.PreferenceRepo;
import com.surf0335.AI_Recommendation_System.repository.StudentRepo;
import com.surf0335.AI_Recommendation_System.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreferenceService {

    @Autowired
    private PreferenceRepo preferenceRepository;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private StudentRepo studentRepo;

    // 获取所有偏好
    public List<Preference> getAllPreferences() {
        return preferenceRepository.findAll();
    }

    // 根据ID获取偏好
    public Preference getPreferenceById(Long id) {
        return preferenceRepository.findById(id).orElse(null);
    }

    // 创建新的偏好
    public Preference createPreference(Preference preference, Integer teacherId, Integer studentId) {
        if (teacherId != null) {
            Teacher teacher = teacherRepo.findById(teacherId).orElse(null);
            preference.setTeacher(teacher);
        }
        if (studentId != null) {
            Student student = studentRepo.findById(studentId).orElse(null);
            preference.setStudent(student);
        }
        return preferenceRepository.save(preference);
    }

    // 更新偏好
    public Preference updatePreference(Long id, Preference updatedPreference, Integer teacherId, Integer studentId) {
        Optional<Preference> existingPreferenceOpt = preferenceRepository.findById(id);
        if (existingPreferenceOpt.isPresent()) {
            Preference existingPreference = existingPreferenceOpt.get();

            // 更新Preference的字段
            existingPreference.setCriteria(updatedPreference.getCriteria());
            existingPreference.setOtherField1(updatedPreference.getOtherField1());
            existingPreference.setOtherField2(updatedPreference.getOtherField2());
            // 如果还有其他字段需要更新，请在此处继续添加...

            // 处理教师和学生的关联
            if (teacherId != null) {
                Teacher teacher = teacherRepo.findById(teacherId).orElse(null);
                existingPreference.setTeacher(teacher);
            }

            if (studentId != null) {
                Student student = studentRepo.findById(studentId).orElse(null);
                existingPreference.setStudent(student);
            }

            return preferenceRepository.save(existingPreference);
        }
        return null;
    }

    // 删除偏好
    public void deletePreference(Long id) {
        preferenceRepository.deleteById(id);
    }
}
