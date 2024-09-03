package com.surf0335.AI_Recommendation_System.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class StudentPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "research_field")
    private String researchField;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "description")
    private String description;

    @Column(name = "order_index")  // 添加这个字段用于排序
    private Integer orderIndex;

    @ManyToOne
    @JoinColumn(name = "preference_list_id")
    @JsonBackReference(value = "studentPreferenceListReference")
    private StudentPreferenceList studentPreferenceList;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResearchField() {
        return researchField;
    }

    public void setResearchField(String researchField) {
        this.researchField = researchField;
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

    public StudentPreferenceList getStudentPreferenceList() {
        return studentPreferenceList;
    }

    public void setStudentPreferenceList(StudentPreferenceList studentPreferenceList) {
        this.studentPreferenceList = studentPreferenceList;
    }
}
