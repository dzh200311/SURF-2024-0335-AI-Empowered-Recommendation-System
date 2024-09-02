package com.surf0335.AI_Recommendation_System.model;

import java.util.List;

public class TeacherPreferenceListDTO {
    private Long id;
    private boolean published;
    private List<TeacherPreferenceDTO> preferences;
    private TeacherPreferenceDTO preference1;
    private TeacherPreferenceDTO preference2;
    private TeacherPreferenceDTO preference3;

    // Getters 和 Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public List<TeacherPreferenceDTO> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<TeacherPreferenceDTO> preferences) {
        this.preferences = preferences;
    }

    public TeacherPreferenceDTO getPreference1() {
        return preference1;
    }

    public void setPreference1(TeacherPreferenceDTO preference1) {
        this.preference1 = preference1;
    }

    public TeacherPreferenceDTO getPreference2() {
        return preference2;
    }

    public void setPreference2(TeacherPreferenceDTO preference2) {
        this.preference2 = preference2;
    }

    public TeacherPreferenceDTO getPreference3() {
        return preference3;
    }

    public void setPreference3(TeacherPreferenceDTO preference3) {
        this.preference3 = preference3;
    }
}
