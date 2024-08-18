package com.surf0335.AI_Recommendation_System.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class StudentPreferenceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "studentPreferenceList", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")  // 确保按 orderIndex 排序
    private List<StudentPreference> preferences = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preference1_id")
    private StudentPreference preference1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preference2_id")
    private StudentPreference preference2;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preference3_id")
    private StudentPreference preference3;

    private boolean published;

    // 添加偏好并自动设置顺序
    public void addPreference(StudentPreference preference) {
        if (preferences.size() >= 3) {
            throw new IllegalStateException("Cannot add more than 3 preferences.");
        }

        preference.setOrderIndex(preferences.size() + 1);  // 设置 orderIndex
        preference.setStudentPreferenceList(this);
        preferences.add(preference);
    }

    // 移除偏好并重新排序
    public void removePreference(StudentPreference preference) {
        preferences.remove(preference);
        preference.setStudentPreferenceList(null);
        reorderPreferences();  // 重新排序
    }

    // 更新偏好
    public void updatePreference(StudentPreference preference) {
        for (StudentPreference pref : preferences) {
            if (pref.getId().equals(preference.getId())) {
                pref.setResearchField(preference.getResearchField());
                pref.setWeight(preference.getWeight());
                pref.setDescription(preference.getDescription());
                break;
            }
        }
    }

    // 重新排序偏好列表
    private void reorderPreferences() {
        for (int i = 0; i < preferences.size(); i++) {
            preferences.get(i).setOrderIndex(i + 1);
        }
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<StudentPreference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<StudentPreference> preferences) {
        this.preferences = preferences;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    // 新增的三个方法，用于设置前三个偏好
    public void setPreference1(StudentPreference preference1) {
        this.preference1 = preference1;
    }

    public StudentPreference getPreference1() {
        return preference1;
    }

    public void setPreference2(StudentPreference preference2) {
        this.preference2 = preference2;
    }

    public StudentPreference getPreference2() {
        return preference2;
    }

    public void setPreference3(StudentPreference preference3) {
        this.preference3 = preference3;
    }

    public StudentPreference getPreference3() {
        return preference3;
    }
}
