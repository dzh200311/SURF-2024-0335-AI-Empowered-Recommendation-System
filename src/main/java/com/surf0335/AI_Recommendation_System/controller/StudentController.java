package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.Student;
import com.surf0335.AI_Recommendation_System.repository.StudentRepo;
import com.surf0335.AI_Recommendation_System.services.StoreImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private StoreImage storeImage;

    @GetMapping("/list")
    public String listStudents(Model theModel) {
        List<Student> students = studentRepository.findAll();
        theModel.addAttribute("students", students);
        return "students/list-students";
    }

    @PostMapping("/save")
    public String createOrUpdateStudent(@RequestParam(name ="file", required = false) MultipartFile file, @ModelAttribute("student") Student student) {
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String url = storeImage.store(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/")
                    .path(fileName)
                    .toUriString();

            student.setAvatarUrl(fileDownloadUri);
        } else if (student.getId() != 0) {
            Optional<Student> existingStudent = studentRepository.findById(student.getId());
            if (existingStudent.isPresent() && existingStudent.get().getAvatarUrl() != null) {
                student.setAvatarUrl(existingStudent.get().getAvatarUrl());
            }
        }
        studentRepository.save(student);
        return "redirect:/students/list";
    }

    @GetMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable int studentId, RedirectAttributes redirectAttributes) {
        try {
            studentRepository.deleteById(studentId);
            redirectAttributes.addFlashAttribute("successMessage", "学生已成功删除。");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "错误：无法找到该学生。");
        }
        return "redirect:/students/list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Student theStudent = new Student();
        theModel.addAttribute("student", theStudent);
        return "students/student-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
        Optional<Student> theStudent = studentRepository.findById(theId);
        if (theStudent.isPresent()) {
            theModel.addAttribute("student", theStudent.get());
            return "students/student-form-update";
        } else {
            return "redirect:/students/list";
        }
    }

    @GetMapping("/showFormForReadonly")
    public String showFormForReadonly(@RequestParam("studentId") int theId, Model theModel) {
        Optional<Student> theStudent = studentRepository.findById(theId);
        if (theStudent.isPresent()) {
            theModel.addAttribute("student", theStudent.get());
            return "students/student-form-readonly";
        } else {
            return "redirect:/students/list";
        }
    }
}
