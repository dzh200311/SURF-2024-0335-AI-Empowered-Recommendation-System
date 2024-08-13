package com.surf0335.AI_Recommendation_System.repository;

import com.surf0335.AI_Recommendation_System.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepo extends JpaRepository<Preference, Long> {
}
