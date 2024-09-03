package com.surf0335.AI_Recommendation_System.services;

import com.surf0335.AI_Recommendation_System.model.Message;
import com.surf0335.AI_Recommendation_System.model.Order;
import com.surf0335.AI_Recommendation_System.model.Student;
import com.surf0335.AI_Recommendation_System.model.Teacher;
import com.surf0335.AI_Recommendation_System.repository.MessageRepository;
import com.surf0335.AI_Recommendation_System.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void sendMessage(Student sender, Teacher receiver) {
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setSentTime(LocalDateTime.now());

        // 添加日志记录
        System.out.println("Saving message: " + message);

        messageRepository.save(message);
    }

    public List<Message> getMessagesForTeacher(int teacherId) {
        return messageRepository.findByReceiverId(teacherId);
    }
}
    

