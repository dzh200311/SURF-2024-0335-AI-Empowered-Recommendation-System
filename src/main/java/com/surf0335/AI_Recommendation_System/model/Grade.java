package com.surf0335.AI_Recommendation_System.model;

import jakarta.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int mark;

    @ManyToOne
    @JoinColumn(name = "modulecode", referencedColumnName = "code")
    private Module module;

    @ManyToOne
    @JoinColumn(name = "studentid", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacherid", referencedColumnName = "id")
    private Teacher teacher;

    @Column(name = "studentname")
    private String studentname;

    public Grade(int id, int mark, Module module, Student student, Teacher teacher, String studentname) {
        this.id = id;
        this.mark = mark;
        this.module = module;
        this.student = student;
        this.teacher = teacher;
        this.studentname = studentname;
    }

    public Grade() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
}
