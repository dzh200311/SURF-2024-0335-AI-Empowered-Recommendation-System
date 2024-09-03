package com.surf0335.AI_Recommendation_System.model;

import jakarta.persistence.*;

@Entity
public class TeacherPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "requirement")
    private String requirement;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "description")
    private String description;

    @Column(name = "order_index")  // 用于排序
    private Integer orderIndex;

    @ManyToOne
    @JoinColumn(name = "preference_list_id")
    private TeacherPreferenceList teacherPreferenceList;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    // Getters 和 Setters 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public TeacherPreferenceList getTeacherPreferenceList() {
        return teacherPreferenceList;
    }

    public void setTeacherPreferenceList(TeacherPreferenceList teacherPreferenceList) {
        this.teacherPreferenceList = teacherPreferenceList;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
