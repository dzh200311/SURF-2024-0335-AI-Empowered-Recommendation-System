package com.surf0335.AI_Recommendation_System.repository;

import com.surf0335.AI_Recommendation_System.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    // 根据学生姓名查找学生
    List<Student> findByStudentname(String studentname);

    // 根据电子邮件查找学生
    List<Student> findByEmail(String email);

    // 根据性别查找学生
    List<Student> findByGender(String gender);

    // 根据年龄查找学生
    List<Student> findByAge(int age);

    // 根据模块名称查找学生
    List<Student> findByModules_Name(String moduleName);
    
    default Optional<Student> findById(Long id) {
        return findById(id.intValue());
    }
   
        Student findByUserId(int userId);
    
    
}
