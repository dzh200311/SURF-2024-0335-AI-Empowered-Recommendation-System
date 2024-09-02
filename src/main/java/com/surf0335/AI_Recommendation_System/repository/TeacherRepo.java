package com.surf0335.AI_Recommendation_System.repository;

import com.surf0335.AI_Recommendation_System.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Integer> {

    // 根据教师姓名查找教师
    List<Teacher> findByTeachername(String teachername);

    // 根据电子邮件查找教师
    List<Teacher> findByEmail(String email);

    // 根据性别查找教师
    List<Teacher> findByGender(String gender);

    // 根据年龄查找教师
    List<Teacher> findByAge(int age);

    // 根据所需的分数查找教师
    List<Teacher> findByRequiredgrade(int requiredgrade);

    default Optional<Teacher> findById(Long id) {
        return findById(id.intValue());
    }
}
