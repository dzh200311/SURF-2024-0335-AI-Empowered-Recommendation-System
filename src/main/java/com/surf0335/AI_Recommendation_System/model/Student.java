package com.surf0335.AI_Recommendation_System.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "age")
    private int age;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "module")
    private String module;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "studentname")
    private String studentname;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Preference> preferences;

    public Student() {
    }

    public Student(int id, int age, String avatarUrl, String description, String email, String gender, String module, String password, String phone, String studentname) {
        this.id = id;
        this.age = age;
        this.avatarUrl = avatarUrl;
        this.description = description;
        this.email = email;
        this.gender = gender;
        this.module = module;
        this.password = password;
        this.phone = phone;
        this.studentname = studentname;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public Set<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<Preference> preferences) {
        this.preferences = preferences;
    }
}
