package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.Grade;
import com.surf0335.AI_Recommendation_System.model.Module;
import com.surf0335.AI_Recommendation_System.model.Teacher;
import com.surf0335.AI_Recommendation_System.repository.GradeRepository;
import com.surf0335.AI_Recommendation_System.repository.ModuleRepo;
import com.surf0335.AI_Recommendation_System.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private ModuleRepo moduleRepository;

    @Autowired
    private TeacherRepo teacherRepository;

    @GetMapping("/list")
    public String listGrades(Model theModel) {
        List<Grade> grades = gradeRepository.findAll();
        theModel.addAttribute("grades", grades);
        return "grades/list-grades";
    }

    @GetMapping("/student/{studentId}/module/{moduleCode}")
    public String getGradeForStudentInModule(@PathVariable int studentId, @PathVariable String moduleCode, Model theModel) {
        List<Grade> grades = gradeRepository.findByStudentIdAndModuleCode(studentId, moduleCode);
        theModel.addAttribute("grades", grades);

        Optional<Module> module = moduleRepository.findByCode(moduleCode);
        if (module.isPresent()) {
            theModel.addAttribute("module", module.get());
        } else {
            theModel.addAttribute("moduleNotFound", "未找到指定模块");
        }

        return "grades/list-grades";
    }

    @GetMapping("/compare/{studentId}/module/{moduleCode}/teacher/{teacherId}")
    public String compareStudentGradeWithTeacherRequirement(@PathVariable int studentId, @PathVariable String moduleCode, @PathVariable int teacherId, Model theModel) {
        List<Grade> grades = gradeRepository.findByStudentIdAndModuleCode(studentId, moduleCode);
        theModel.addAttribute("grades", grades);

        Optional<Module> module = moduleRepository.findByCode(moduleCode);
        if (module.isPresent()) {
            theModel.addAttribute("module", module.get());
        } else {
            theModel.addAttribute("moduleNotFound", "未找到指定模块");
        }

        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isPresent()) {
            theModel.addAttribute("teacher", teacher.get());
        } else {
            theModel.addAttribute("teacherNotFound", "未找到指定老师");
        }

        if (!grades.isEmpty() && module.isPresent() && teacher.isPresent()) {
            Grade studentGrade = grades.get(0); 
            int requiredGrade = teacher.get().getRequiredgrade();
            boolean meetsRequirement = studentGrade.getMark() >= requiredGrade;
            theModel.addAttribute("meetsRequirement", meetsRequirement);
            theModel.addAttribute("requiredGrade", requiredGrade);
        }

        return "grades/compare-grade";
    }
}
