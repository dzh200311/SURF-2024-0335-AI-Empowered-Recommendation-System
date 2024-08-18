package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.StudentPreference;
import com.surf0335.AI_Recommendation_System.model.Teacher;
import com.surf0335.AI_Recommendation_System.repository.TeacherRepo;
import com.surf0335.AI_Recommendation_System.services.StoreImage;
import com.surf0335.AI_Recommendation_System.services.TeacherService;
import com.surf0335.AI_Recommendation_System.services.StudentPreferenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/save")
    public String createOrUpdateTeacher(@RequestParam(name = "file", required = false) MultipartFile file, @ModelAttribute("teacher") Teacher teacher) {
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String url = storeImage.store(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/")
                    .path(fileName)
                    .toUriString();

            teacher.setAvatarUrl(fileDownloadUri);
        } else if (teacher.getId() != 0) {
            Optional<Teacher> existingTeacher = teacherRepository.findById(teacher.getId());
            if (existingTeacher.isPresent() && existingTeacher.get().getAvatarUrl() != null) {
                teacher.setAvatarUrl(existingTeacher.get().getAvatarUrl());
            }
        }
        teacherRepository.save(teacher);
        return "redirect:/teachers/list";
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

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Teacher theTeacher = new Teacher();
        theModel.addAttribute("teacher", theTeacher);
        return "teachers/teacher-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("teacherId") int theId, Model theModel) {
        Optional<Teacher> theTeacher = teacherRepository.findById(theId);
        if (theTeacher.isPresent()) {
            theModel.addAttribute("teacher", theTeacher.get());
            return "teachers/teacher-form-update";
        } else {
            return "redirect:/teachers/list";
        }
    }

    @GetMapping("/scoreAndSortTeachers")
    public ResponseEntity<List<Teacher>> getScoreAndSortedTeachers(@RequestParam("studentId") int studentId) {
        List<StudentPreference> preferences = studentPreferenceService.getPreferencesForStudent(studentId);
        List<Teacher> sortedTeachers = teacherService.getTeachersByStudentPreferences(studentId);
        return ResponseEntity.ok(sortedTeachers);
    }

    @GetMapping("/showFormForReadonly")
    public String showFormForReadonly(@RequestParam("teacherId") int theId, Model theModel) {
        Optional<Teacher> theTeacher = teacherRepository.findById(theId);
        if (theTeacher.isPresent()) {
            theModel.addAttribute("teacher", theTeacher.get());
            return "teachers/teacher-form-readonly";
        } else {
            return "redirect:/teachers/list";
        }
    }
}
