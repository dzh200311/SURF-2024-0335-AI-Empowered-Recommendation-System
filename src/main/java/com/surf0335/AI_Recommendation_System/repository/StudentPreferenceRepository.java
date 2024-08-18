package com.surf0335.AI_Recommendation_System.repository;

import com.surf0335.AI_Recommendation_System.model.StudentPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentPreferenceRepository extends JpaRepository<StudentPreference, Integer> {

    List<StudentPreference> findByStudentPreferenceListStudentId(int studentId);

}
