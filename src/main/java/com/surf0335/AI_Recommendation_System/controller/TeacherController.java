package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.Student;
import com.surf0335.AI_Recommendation_System.model.StudentPreference;
import com.surf0335.AI_Recommendation_System.model.Teacher;
import com.surf0335.AI_Recommendation_System.model.TeacherPreference;
import com.surf0335.AI_Recommendation_System.model.User;
import com.surf0335.AI_Recommendation_System.repository.TeacherRepo;
import com.surf0335.AI_Recommendation_System.services.StoreImage;
import com.surf0335.AI_Recommendation_System.services.StudentPreferenceService;
import com.surf0335.AI_Recommendation_System.services.TeacherService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepo teacherRepository;

    @Autowired
    private StoreImage storeImage;

    @Autowired
    private StudentPreferenceService studentPreferenceService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public String listTeachers(Model theModel) {
        List<Teacher> teachers = teacherRepository.findAll();
        theModel.addAttribute("teachers", teachers);
        return "teachers/list-teachers";
    }
    @GetMapping("/teacherprofilereadonly")
    public String getTeacherProfileReadonly(@RequestParam("teacherId") int teacherId, Model model) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        System.out.println("Controller method invoked with teacherId: " + teacherId);
        if (teacher == null) {
            // 可以添加日志或者抛出异常来查看是否获取到了教师数据
            return "error"; // 返回一个错误页面或抛出一个异常
        }
        model.addAttribute("teacher", teacher);
        if (teacher == null) {
            System.out.println("No teacher found with ID: " + teacherId);
            return "error"; 
        }
        
        return "teacherprofilereadonly";  // 返回 Thymeleaf 模板名
    }
    
    @Transactional
    @PostMapping("/save")
    public String createOrUpdateTeacher(@RequestParam(name = "file", required = false) MultipartFile file, @ModelAttribute("teacher") Teacher teacher, HttpSession session) {
        Teacher updatedTeacher = teacher; // 使用一个新的变量来保存修改后的教师对象
    
        if (file != null && !file.isEmpty()) {
            String fileName = storeImage.store(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();
            updatedTeacher.setAvatarUrl(fileDownloadUri);
        } else if (teacher.getId() != 0) {
            Optional<Teacher> existingTeacherOptional = teacherRepository.findById(teacher.getId());
            if (existingTeacherOptional.isPresent()) {
                Teacher existingTeacher = existingTeacherOptional.get();
    
                // 保留原有的 Avatar URL
                if (existingTeacher.getAvatarUrl() != null) {
                    updatedTeacher.setAvatarUrl(existingTeacher.getAvatarUrl());
                }
    
                // 更新集合而不是替换
                if (teacher.getPreferences() != null) {
                    existingTeacher.getPreferences().clear();
                    existingTeacher.getPreferences().addAll(teacher.getPreferences());
                }
    
                // 确保所有其他字段也被更新
                existingTeacher.setTeachername(teacher.getTeachername());
                existingTeacher.setAge(teacher.getAge());
                existingTeacher.setEmail(teacher.getEmail());
                existingTeacher.setGender(teacher.getGender());
                existingTeacher.setPhone(teacher.getPhone());
                existingTeacher.setRequiredgrade(teacher.getRequiredgrade());
                existingTeacher.setDescription(teacher.getDescription());
                
                updatedTeacher = existingTeacher; // 使用修改后的 existingTeacher 对象替换 updatedTeacher 对象
            }
        }
    
        // 从 Session 中获取 User 并设置给 Teacher
        User user = (User) session.getAttribute("user");
        if (user != null) {
            updatedTeacher.setUser(user);
        }
    
        // 保存 Teacher 对象
        teacherRepository.save(updatedTeacher);
    
        // 更新 Session 中的 User 对象（主要是更新与 User 相关联的 Teacher）
        if (user != null) {
            user.setTeacher(updatedTeacher);
            session.setAttribute("user", user);
        }
    
        return "redirect:/teachers/profile?teacherId=" + updatedTeacher.getId();
    }
    

    @GetMapping("/profile")
    public String showTeacherProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user != null && "teacher".equals(user.getRole())) {
            Teacher teacher = user.getTeacher(); // 直接通过 User 实体获取关联的 Teacher 对象

            if (teacher != null) {
                model.addAttribute("teacher", teacher);
                model.addAttribute("noTeacherInfo", false); // 没有问题时，标志为 false
            } else {
                model.addAttribute("noTeacherInfo", true); // 没有教师信息，设置标志为 true
            }

            // 返回教师个人资料页面
            return "teacherprofile"; 
        }

        // 用户未登录或角色不匹配时，可以重定向到登录页面或其他适合的页面
        return "redirect:/log_in"; 
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null && "teacher".equals(user.getRole())) {
            // 直接从 User 对象中获取关联的 Teacher 对象
            Teacher teacher = user.getTeacher();
            if (teacher != null) {
                model.addAttribute("teacher", teacher);
            } else {
                // 如果没有找到教师信息，创建一个空的 Teacher 对象
                model.addAttribute("teacher", new Teacher());
            }
            return "updateteacherprofile"; // 返回更新教师信息的模板路径
        }
        // 返回一个空的 Teacher 对象，即使用户不符合条件也显示空表单
        model.addAttribute("teacher", new Teacher());
        return "updateteacherprofile";
    }

    @GetMapping("/delete/{teacherId}")
    public String deleteTeacher(@PathVariable int teacherId, RedirectAttributes redirectAttributes) {
        try {
            teacherRepository.deleteById(teacherId);
            redirectAttributes.addFlashAttribute("successMessage", "Teacher has been successfully deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: Teacher could not be found.");
        }
        return "redirect:/teachers/list";
    }

    @GetMapping("/sortedTeachersByPreference")
    public String showSortedTeachersByPreference(@RequestParam("studentId") int studentId, Model model) {
        List<Teacher> sortedTeachers = teacherService.getTeachersByStudentPreferences(studentId);
        model.addAttribute("teachers", sortedTeachers);
        return "teachers/sorted-teachers";
    }

    @GetMapping("/scoreAndSortTeachers")
    public ResponseEntity<List<Teacher>> getScoreAndSortedTeachers(@RequestParam("studentId") int studentId) {
        List<StudentPreference> preferences = studentPreferenceService.getPreferencesForStudent(studentId);
        List<Teacher> sortedTeachers = teacherService.getTeachersByStudentPreferences(studentId);
        return ResponseEntity.ok(sortedTeachers);
    }

    @GetMapping("/showFormForReadonly")
    public String showFormForReadonly(@RequestParam("teacherId") int teacherId, Model model) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isPresent()) {
            model.addAttribute("teacher", teacher.get());
            return "teachers/teacher-form-readonly";
        } else {
            return "redirect:/teachers/list";
        }
    }

    @GetMapping("/teachersByStudentModule")
    public ResponseEntity<List<Teacher>> getTeachersByStudentModule(@RequestParam("studentId") int studentId) {
        List<Teacher> sortedTeachers = teacherService.getTeachersByStudentPreferences(studentId);
        return ResponseEntity.ok(sortedTeachers);
    }
   

}
