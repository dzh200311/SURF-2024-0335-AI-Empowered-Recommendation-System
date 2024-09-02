package com.surf0335.AI_Recommendation_System.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TeacherPreferenceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Teacher teacher;

    @OneToMany(mappedBy = "teacherPreferenceList", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    private List<TeacherPreference> preferences = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preference1_id")
    private TeacherPreference preference1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preference2_id")
    private TeacherPreference preference2;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preference3_id")
    private TeacherPreference preference3;

    private boolean published;

    // 添加偏好并自动设置顺序
    public void addPreference(TeacherPreference preference) {
        if (preferences.size() >= 3) {
            throw new IllegalStateException("Cannot add more than 3 preferences.");
        }

        preference.setOrderIndex(preferences.size() + 1);  // 设置 orderIndex
        preference.setTeacherPreferenceList(this);
        preferences.add(preference);
    }

    // 移除偏好并重新排序
    public void removePreference(TeacherPreference preference) {
        preferences.remove(preference);
        preference.setTeacherPreferenceList(null);
        reorderPreferences();  // 重新排序
    }

    // 重新排序偏好列表
    private void reorderPreferences() {
        for (int i = 0; i < preferences.size(); i++) {
            preferences.get(i).setOrderIndex(i + 1);
        }
    }

    // Getters 和 Setters 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<TeacherPreference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<TeacherPreference> preferences) {
        this.preferences = preferences;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    // 新增的三个方法，用于设置前三个偏好
    public void setPreference1(TeacherPreference preference1) {
        this.preference1 = preference1;
    }

    public TeacherPreference getPreference1() {
        return preference1;
    }

    public void setPreference2(TeacherPreference preference2) {
        this.preference2 = preference2;
    }

    public TeacherPreference getPreference2() {
        return preference2;
    }

    public void setPreference3(TeacherPreference preference3) {
        this.preference3 = preference3;
    }

    public TeacherPreference getPreference3() {
        return preference3;
    }
}
