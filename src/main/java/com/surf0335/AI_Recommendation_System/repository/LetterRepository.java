package com.surf0335.AI_Recommendation_System.repository;

import com.surf0335.AI_Recommendation_System.model.Letter;
import com.surf0335.AI_Recommendation_System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetterRepository extends JpaRepository<Letter, String> {
    List<Letter> findByUserUsername(String username);

    Letter findByPadIdAndUser(String padId, User user); // 根据Letter ID和用户查找信件
}
