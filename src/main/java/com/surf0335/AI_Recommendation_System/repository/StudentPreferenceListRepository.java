package com.surf0335.AI_Recommendation_System.repository;

import com.surf0335.AI_Recommendation_System.model.StudentPreferenceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPreferenceListRepository extends JpaRepository<StudentPreferenceList, Integer> {
    StudentPreferenceList findByStudentId(int studentId);
}
