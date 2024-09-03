package com.surf0335.AI_Recommendation_System.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Transient
    private Double score = 0.0;

    private String teachername;
    private int age;
    private String email;
    private String gender;
    private String phone;
    private int requiredgrade;
    private String description;
    private String avatarUrl;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeacherPreference> preferences;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Module> modules;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToOne(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnore
    private TeacherPreferenceList preferenceList;

     @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private Set<Message> receivedMessages = new HashSet<>();

    // Getters 和 Setters 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Set<TeacherPreference> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<TeacherPreference> preferences) {
        this.preferences = preferences;
    }

    public TeacherPreferenceList getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(TeacherPreferenceList preferenceList) {
        this.preferenceList = preferenceList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
