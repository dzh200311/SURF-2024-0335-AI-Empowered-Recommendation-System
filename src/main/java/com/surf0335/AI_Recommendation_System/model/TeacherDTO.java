package com.surf0335.AI_Recommendation_System.model;

import java.util.Set;

public class TeacherDTO {
    private int id;
    private String teachername;
    private int age;
    private String email;
    private String gender;
    private String phone;
    private int requiredgrade;
    private String description;
    private String avatarUrl;
    private Double score;
    private Set<TeacherPreferenceDTO> preferences;

    // Getters 和 Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRequiredgrade() {
        return requiredgrade;
    }

    public void setRequiredgrade(int requiredgrade) {
        this.requiredgrade = requiredgrade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Set<TeacherPreferenceDTO> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<TeacherPreferenceDTO> preferences) {
        this.preferences = preferences;
    }
}
