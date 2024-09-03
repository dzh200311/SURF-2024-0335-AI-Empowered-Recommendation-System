package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.TeacherPreferenceDTO;
import com.surf0335.AI_Recommendation_System.model.TeacherPreferenceListDTO;
import com.surf0335.AI_Recommendation_System.model.TeacherPreference;
import com.surf0335.AI_Recommendation_System.model.TeacherPreferenceList;
import com.surf0335.AI_Recommendation_System.services.TeacherPreferenceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/teacher-preference-lists")
public class TeacherPreferenceListController {

    @Autowired
    private TeacherPreferenceListService preferenceListService;

    // 获取某个教师的偏好列表
    @GetMapping("/list")
    public String listPreferencesByTeacher(@RequestParam("teacherId") int teacherId, Model model) {
        TeacherPreferenceList preferenceList = preferenceListService.getPreferenceListByTeacherId(teacherId);
        TeacherPreferenceListDTO preferenceListDTO = convertToDTO(preferenceList);
        model.addAttribute("preferenceList", preferenceListDTO);
        return "preference-lists/list-preferences";
    }

    // 保存偏好列表
    @PostMapping("/save")
    public String savePreferenceList(@ModelAttribute("preferenceList") TeacherPreferenceListDTO preferenceListDTO) {
        TeacherPreferenceList preferenceList = convertToEntity(preferenceListDTO);
        preferenceListService.savePreferenceList(preferenceList);
        return "redirect:/teacher-preference-lists/list?teacherId=" + preferenceList.getTeacher().getId();
    }

    // 辅助方法：将实体类转换为DTO
    private TeacherPreferenceListDTO convertToDTO(TeacherPreferenceList preferenceList) {
        TeacherPreferenceListDTO dto = new TeacherPreferenceListDTO();
        dto.setId(preferenceList.getId());
        dto.setPublished(preferenceList.isPublished());
        
        // 设置偏好列表
        dto.setPreferences(convertToDTOList(preferenceList.getPreferences()));
        
        // 设置单独的偏好项
        dto.setPreference1(convertToDTO(preferenceList.getPreference1()));
        dto.setPreference2(convertToDTO(preferenceList.getPreference2()));
        dto.setPreference3(convertToDTO(preferenceList.getPreference3()));
        
        return dto;
    }

    // 辅助方法：DTO转换为实体类
    private TeacherPreferenceList convertToEntity(TeacherPreferenceListDTO dto) {
        TeacherPreferenceList preferenceList = new TeacherPreferenceList();
        preferenceList.setId(dto.getId());
        preferenceList.setPublished(dto.isPublished());
        
        // 设置偏好列表
        preferenceList.setPreferences(convertToEntityList(dto.getPreferences()));
        
        // 设置单独的偏好项
        preferenceList.setPreference1(convertToEntity(dto.getPreference1()));
        preferenceList.setPreference2(convertToEntity(dto.getPreference2()));
        preferenceList.setPreference3(convertToEntity(dto.getPreference3()));
        
        return preferenceList;
    }

    // 辅助方法：将偏好实体类列表转换为DTO列表
    private List<TeacherPreferenceDTO> convertToDTOList(List<TeacherPreference> preferences) {
        List<TeacherPreferenceDTO> dtoList = new ArrayList<>();
        if (preferences != null) {
            for (TeacherPreference preference : preferences) {
                dtoList.add(convertToDTO(preference));
            }
        }
        return dtoList;
    }

    // 辅助方法：DTO转换为偏好实体类列表
    private List<TeacherPreference> convertToEntityList(List<TeacherPreferenceDTO> dtoList) {
        List<TeacherPreference> preferenceList = new ArrayList<>();
        if (dtoList != null) {
            for (TeacherPreferenceDTO dto : dtoList) {
                preferenceList.add(convertToEntity(dto));
            }
        }
        return preferenceList;
    }

    // 辅助方法：将单个偏好实体类转换为DTO
    private TeacherPreferenceDTO convertToDTO(TeacherPreference preference) {
        if (preference == null) return null;
        TeacherPreferenceDTO dto = new TeacherPreferenceDTO();
        dto.setId(preference.getId());
        dto.setRequirement(preference.getRequirement());
        dto.setWeight(preference.getWeight());
        dto.setDescription(preference.getDescription());
        dto.setOrderIndex(preference.getOrderIndex());
        return dto;
    }

    // 辅助方法：DTO转换为单个偏好实体类
    private TeacherPreference convertToEntity(TeacherPreferenceDTO dto) {
        if (dto == null) return null;
        TeacherPreference preference = new TeacherPreference();
        preference.setId(dto.getId());
        preference.setRequirement(dto.getRequirement());
        preference.setWeight(dto.getWeight());
        preference.setDescription(dto.getDescription());
        preference.setOrderIndex(dto.getOrderIndex());
        return preference;
    }
}
