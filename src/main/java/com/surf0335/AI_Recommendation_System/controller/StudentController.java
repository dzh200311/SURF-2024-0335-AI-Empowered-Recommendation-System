package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.Module;
import com.surf0335.AI_Recommendation_System.model.Student;
import com.surf0335.AI_Recommendation_System.model.User;
import com.surf0335.AI_Recommendation_System.repository.ModuleRepo;
import com.surf0335.AI_Recommendation_System.repository.StudentRepo;
import com.surf0335.AI_Recommendation_System.services.StoreImage;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;
import org.hibernate.Hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private ModuleRepo moduleRepository;

    @Autowired
    private StoreImage storeImage;

    // @GetMapping("/list")
    // public String listStudents(Model theModel) {
    //     List<Student> students = studentRepository.findAll();
    //     theModel.addAttribute("students", students);
    //     return "students/list-students"; // 确保路径 students/list-students.html 存在
    // }

    @PostMapping("/save")
public String createOrUpdateStudent(@RequestParam(name = "file", required = false) MultipartFile file, @ModelAttribute("student") Student student, HttpSession session) {
    if (file != null && !file.isEmpty()) {
        String fileName = storeImage.store(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(fileName)
                .toUriString();
        student.setAvatarUrl(fileDownloadUri);
    } else if (student.getId() != 0) {
        Optional<Student> existingStudent = studentRepository.findById(student.getId());
        existingStudent.ifPresent(value -> student.setAvatarUrl(value.getAvatarUrl()));
    }

    // 从 Session 中获取 User 并设置给 Student
    User user = (User) session.getAttribute("user");
    if (user != null) {
        student.setUser(user);
    }

    // 保存 Student 对象
    studentRepository.save(student);

    // 更新 Session 中的 User 对象（主要是更新与 User 相关联的 Student）
    if (user != null) {
        user.setStudent(student);
        session.setAttribute("user", user);
    }

    return "redirect:/students/profile";
}


    @GetMapping("/profile")
@Transactional
public String showStudentProfile(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    
    if (user != null && "student".equals(user.getRole())) {
        // 从 User 对象中获取关联的 Student 对象
        Student student = user.getStudent();
        
        if (student != null) {
            // 强制初始化模块集合
            Hibernate.initialize(student.getModules());
            
            model.addAttribute("student", student);
            return "studentprofile"; // 返回学生个人资料页面，确保路径 studentprofile.html 存在
        } else {
            model.addAttribute("noStudentInfo", true); // 没有学生信息，设置标志为 true
        }
    }
    
    // 如果用户未登录、角色不匹配或没有学生信息，重定向到登录页面
    return "redirect:/log_in"; 
}

    
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Student theStudent = new Student();
        theModel.addAttribute("student", theStudent);
        theModel.addAttribute("allModules", moduleRepository.findAll()); // 添加所有模块的列表
        return "addstudent"; // 确保路径 addstudent.html 存在
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(HttpSession session, Model theModel) {
        User user = (User) session.getAttribute("user");
        if (user != null && "student".equals(user.getRole())) {
            // 修改为直接从 User 对象中获取关联的 Student 对象
            Student student = user.getStudent();
            if (student != null) {
                theModel.addAttribute("student", student);
            } else {
                // 如果没有找到学生信息，创建一个空的 Student 对象
                theModel.addAttribute("student", new Student());
            }
            theModel.addAttribute("allModules", moduleRepository.findAll());
            return "addstudent";
        }
        // 返回一个空的 Student 对象，即使用户不符合条件也显示空表单
        theModel.addAttribute("student", new Student());
        theModel.addAttribute("allModules", moduleRepository.findAll());
        return "addstudent";
    }

    @GetMapping("/showFormForReadonly")
    public String showFormForReadonly(@RequestParam("studentId") int studentId, Model theModel) {
        Optional<Student> theStudent = studentRepository.findById(studentId);
        if (theStudent.isPresent()) {
            theModel.addAttribute("student", theStudent.get());
            return "students/student-form-readonly"; // 确保路径 students/student-form-readonly.html 存在
        } else {
            return "redirect:/students/list";
        }
    }

    @GetMapping("/studentdashboard")
    public String getStudentDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null && "student".equals(user.getRole())) {
            model.addAttribute("user", user);
            return "studentdashboard";  // 确保路径 studentdashboard.html 存在
        }
        return "redirect:/log_in";
    }
}
