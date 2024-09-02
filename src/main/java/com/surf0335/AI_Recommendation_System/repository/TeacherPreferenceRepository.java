package com.surf0335.AI_Recommendation_System.repository;

import com.surf0335.AI_Recommendation_System.model.TeacherPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherPreferenceRepository extends JpaRepository<TeacherPreference, Integer> {

    List<TeacherPreference> findByTeacherPreferenceListTeacherId(int teacherId);
    
}
