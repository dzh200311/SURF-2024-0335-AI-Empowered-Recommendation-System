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

        List<Module> modules = moduleRepository.findByCode(moduleCode);
        if (!modules.isEmpty()) {
            theModel.addAttribute("modules", modules);
        } else {
            theModel.addAttribute("moduleNotFound", "未找到指定模块");
        }

        return "grades/list-grades";
    }

    @GetMapping("/compare/{studentId}/module/{moduleCode}")
    public String compareStudentGradeWithTeacherRequirement(@PathVariable int studentId, @PathVariable String moduleCode, Model theModel) {
        // 获取学生在指定模块的成绩
        List<Grade> grades = gradeRepository.findByStudentIdAndModuleCode(studentId, moduleCode);
        theModel.addAttribute("grades", grades);

        // 获取模块信息
        List<Module> modules = moduleRepository.findByCode(moduleCode);
        if (!modules.isEmpty()) {
            Module module = modules.get(0);  // 假设同一代码下的模块使用第一个模块
            theModel.addAttribute("module", module);
            Teacher teacher = module.getTeacher();  // 获取教授该模块的教师信息

            if (teacher != null) {
                theModel.addAttribute("teacher", teacher);
                if (!grades.isEmpty()) {
                    Grade studentGrade = grades.get(0);
                    int requiredGrade = teacher.getRequiredgrade();
                    boolean meetsRequirement = studentGrade.getMark() >= requiredGrade;
                    theModel.addAttribute("meetsRequirement", meetsRequirement);
                    theModel.addAttribute("requiredGrade", requiredGrade);
                }
            } else {
                theModel.addAttribute("teacherNotFound", "未找到指定老师");
            }
        } else {
            theModel.addAttribute("moduleNotFound", "未找到指定模块");
        }

        return "grades/compare-grade";
    }
}
