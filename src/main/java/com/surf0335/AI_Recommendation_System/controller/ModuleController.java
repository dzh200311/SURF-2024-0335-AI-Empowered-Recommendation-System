package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.Module;
import com.surf0335.AI_Recommendation_System.model.Teacher;
import com.surf0335.AI_Recommendation_System.repository.ModuleRepo;
import com.surf0335.AI_Recommendation_System.repository.TeacherRepo;
import com.surf0335.AI_Recommendation_System.services.ModuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleRepo moduleRepository;

    @Autowired
    private TeacherRepo teacherRepository;

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/teachersByStudentModule")
    public ResponseEntity<List<Teacher>> getTeachersByStudentModule(@RequestParam("studentId") int studentId) {
        List<Module> modules = moduleService.getModulesByStudentId(studentId);
        System.out.println("Modules found for studentId " + studentId + ": " + modules.size());
    
        modules.forEach(module -> System.out.println("Module: " + module.getName() + ", Teacher: " + (module.getTeacher() != null ? module.getTeacher().getTeachername() : "No Teacher")));
    
        List<Teacher> teachers = modules.stream()
                .map(Module::getTeacher)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    
        System.out.println("Teachers found: " + teachers.size());
        teachers.forEach(teacher -> System.out.println("Teacher: " + teacher.getTeachername()));
    
        return ResponseEntity.ok(teachers);
    }
    
}
