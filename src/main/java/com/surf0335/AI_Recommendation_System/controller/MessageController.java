package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.Message;
import com.surf0335.AI_Recommendation_System.model.Student;
import com.surf0335.AI_Recommendation_System.model.Teacher;
import com.surf0335.AI_Recommendation_System.repository.StudentRepo;
import com.surf0335.AI_Recommendation_System.repository.TeacherRepo;
import com.surf0335.AI_Recommendation_System.services.MessageService;
import com.surf0335.AI_Recommendation_System.services.TeacherService;

import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import java.util.List;
import com.surf0335.AI_Recommendation_System.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MessageController {
  @Autowired
    private TeacherService teacherService;
        @Autowired
    private MessageService messageService;

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private TeacherRepo teacherRepository;
    @PostMapping("/students/sendMessage")
    @Transactional
    public String sendMessage(@RequestParam int userId, @RequestParam int teacherId, RedirectAttributes redirectAttributes) {
        System.out.println("sendMessage method called with userId: " + userId + " and teacherId: " + teacherId);
        
        // 假设你有一个方法可以通过userId查找对应的Student
        Student student = studentRepository.findByUserId(userId);
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
    
        if (student != null && teacher != null) {
            System.out.println("Student and Teacher found. Sending message...");
            messageService.sendMessage(student, teacher);
            redirectAttributes.addFlashAttribute("messageSentSuccess", true);
        } else {
            System.out.println("Student or Teacher not found. student: " + student + ", teacher: " + teacher);
        }
    
        return "redirect:/teachers/teacherprofilereadonly?teacherId=" + teacherId;
    }
    
    
    @GetMapping("/teacher/messages")
    public String getTeacherMessages(@RequestParam int teacherId, Model model) {
        // 获取指定老师的所有消息
        List<Message> messages = messageService.getMessagesForTeacher(teacherId);
        model.addAttribute("messages", messages);
        return "teacherdashboard";  // 这个视图应该显示消息列表
    }
    @GetMapping("/studentsByTeacher")
    public ResponseEntity<?> getStudentsByTeacher(@RequestParam("teacherId") int teacherId) {
        try {
            // 获取给教师发送过消息的学生列表
            List<Message> messages = messageService.getMessagesForTeacher(teacherId);
            List<Student> students = messages.stream()
                    .map(Message::getSender)
                    .filter(Objects::nonNull)
                    .distinct()
                    .collect(Collectors.toList());
    
            if (students.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No students found.");
            }
    
            // 根据教师的偏好进行打分
            List<Student> scoredStudents = teacherService.getStudentsByTeacherPreferences(teacherId, students);
    
            // 返回打分后的学生列表
            return ResponseEntity.ok(scoredStudents);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while fetching students.");
        }
    }
    
    
}
