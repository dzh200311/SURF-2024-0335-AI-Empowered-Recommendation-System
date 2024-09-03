package com.surf0335.AI_Recommendation_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.surf0335.AI_Recommendation_System.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByReceiverId(int receiverId);  // 自定义查询方法
}
