package com.surf0335.AI_Recommendation_System.repository;

import com.surf0335.AI_Recommendation_System.model.StudentPreferenceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentPreferenceListRepository extends JpaRepository<StudentPreferenceList, Integer> {
    StudentPreferenceList findByStudentId(int studentId);
     @Modifying
    @Query("UPDATE StudentPreferenceList spl SET spl.preference1 = NULL WHERE spl.preference1.id = :preferenceId")
    void nullifyPreference1(@Param("preferenceId") int preferenceId);

    @Modifying
    @Query("UPDATE StudentPreferenceList spl SET spl.preference2 = NULL WHERE spl.preference2.id = :preferenceId")
    void nullifyPreference2(@Param("preferenceId") int preferenceId);

    @Modifying
    @Query("UPDATE StudentPreferenceList spl SET spl.preference3 = NULL WHERE spl.preference3.id = :preferenceId")
    void nullifyPreference3(@Param("preferenceId") int preferenceId);
}
