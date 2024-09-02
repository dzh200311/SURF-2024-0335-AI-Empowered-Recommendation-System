package com.surf0335.AI_Recommendation_System.repository;

import com.surf0335.AI_Recommendation_System.model.TeacherPreferenceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherPreferenceListRepository extends JpaRepository<TeacherPreferenceList, Integer> {

    TeacherPreferenceList findByTeacherId(int teacherId);
    
    @Modifying
    @Query("UPDATE TeacherPreferenceList tpl SET tpl.preference1 = NULL WHERE tpl.preference1.id = :preferenceId")
    void nullifyPreference1(@Param("preferenceId") int preferenceId);

    @Modifying
    @Query("UPDATE TeacherPreferenceList tpl SET tpl.preference2 = NULL WHERE tpl.preference2.id = :preferenceId")
    void nullifyPreference2(@Param("preferenceId") int preferenceId);

    @Modifying
    @Query("UPDATE TeacherPreferenceList tpl SET tpl.preference3 = NULL WHERE tpl.preference3.id = :preferenceId")
    void nullifyPreference3(@Param("preferenceId") int preferenceId);
}
