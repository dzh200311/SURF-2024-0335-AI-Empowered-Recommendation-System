package com.surf0335.AI_Recommendation_System.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "teachername")
    private String teachername;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "requiredgrade")
    private int requiredgrade;

    @Column(name = "description")
    private String description;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Preference> preferences;

    public Teacher() {
    }

    public Teacher(int id, String teachername, String password, int age, String email, String gender, String phone, int requiredgrade, String description, String avatarUrl) {
        this.id = id;
        this.teachername = teachername;
        this.password = password;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.requiredgrade = requiredgrade;
        this.description = description;
        this.avatarUrl = avatarUrl;
    }

    // Getters and Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<Preference> preferences) {
        this.preferences = preferences;
    }
}
