package com.surf0335.AI_Recommendation_System.services;

import com.surf0335.AI_Recommendation_System.model.Module;
import com.surf0335.AI_Recommendation_System.model.Teacher;
import com.surf0335.AI_Recommendation_System.repository.ModuleRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepo moduleRepo;

    public List<Module> getModulesByStudentId(int studentId) {
        List<Module> modules = moduleRepo.findByStudentsId(studentId);
        
        // 强制初始化 Teacher 对象，避免懒加载问题
        for (Module module : modules) {
            Teacher teacher = module.getTeacher();
            if (teacher != null) {
                Hibernate.initialize(teacher);
            }
        }
        return modules;
    }
}
