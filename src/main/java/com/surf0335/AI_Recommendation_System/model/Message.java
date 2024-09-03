package com.surf0335.AI_Recommendation_System.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore // 防止循环引用
    private Student sender;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonIgnore // 防止循环引用
    private Teacher receiver;

    @JsonIgnore // 防止循环引用
    private LocalDateTime sentTime;

    // Default constructor
    public Message() {
    }

    // Parameterized constructor
    public Message(Student sender, Teacher receiver, LocalDateTime sentTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.sentTime = sentTime;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getSender() {
        return sender;
    }

    public void setSender(Student sender) {
        this.sender = sender;
    }

    public Teacher getReceiver() {
        return receiver;
    }

    public void setReceiver(Teacher receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }
}
