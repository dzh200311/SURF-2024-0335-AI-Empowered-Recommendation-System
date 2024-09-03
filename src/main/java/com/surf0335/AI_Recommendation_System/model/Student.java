package com.surf0335.AI_Recommendation_System.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "student")
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "student_module",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "module_id")
    )
    @JsonIgnore
    private Set<Module> modules = new HashSet<>();

    @Column(name = "phone")
    private String phone;

    @Column(name = "studentname")
    private String studentname;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Grade> grades = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private StudentPreferenceList preferenceList;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private Set<Message> sentMessages = new HashSet<>();
    
    // 新增的字段
    @Transient  // 这个字段不需要持久化到数据库
    private double score;

    // Getters 和 Setters 方法
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

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
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

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StudentPreferenceList getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(StudentPreferenceList preferenceList) {
        this.preferenceList = preferenceList;
    }

    // 新增的 score 字段的 getter 和 setter
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
