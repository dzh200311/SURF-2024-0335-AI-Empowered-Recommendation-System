package com.surf0335.AI_Recommendation_System.repository;

import com.surf0335.AI_Recommendation_System.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    // 根据学生ID和模块代码查找成绩
    List<Grade> findByStudentIdAndModuleCode(int studentId, String moduleCode);

    // 根据教师ID查找成绩
    List<Grade> findByTeacherId(int teacherId);
}
