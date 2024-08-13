package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.Module;
import com.surf0335.AI_Recommendation_System.model.Teacher;
import com.surf0335.AI_Recommendation_System.repository.ModuleRepo;
import com.surf0335.AI_Recommendation_System.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleRepo moduleRepository;

    @Autowired
    private TeacherRepo teacherRepository;

    @GetMapping("/list")
    public String listModules(Model theModel) {
        List<Module> modules = moduleRepository.findAll();
        theModel.addAttribute("modules", modules);
        return "modules/list-modules";
    }

    @PostMapping("/save")
    public String createOrUpdateModule(@ModelAttribute("module") Module module) {
        Optional<Teacher> teacher = teacherRepository.findById(module.getTeacher().getId());
        if (teacher.isPresent()) {
            module.setTeachername(teacher.get().getTeachername());
        }
        moduleRepository.save(module);
        return "redirect:/modules/list";
    }

    @GetMapping("/delete/{moduleId}")
    public String deleteModule(@PathVariable int moduleId, RedirectAttributes redirectAttributes) {
        try {
            moduleRepository.deleteById(moduleId);
            redirectAttributes.addFlashAttribute("successMessage", "模块已成功删除。");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "错误：无法找到该模块。");
        }
        return "redirect:/modules/list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Module theModule = new Module();
        List<Teacher> teachers = teacherRepository.findAll();
        theModel.addAttribute("module", theModule);
        theModel.addAttribute("teachers", teachers);
        return "modules/module-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("moduleId") int theId, Model theModel) {
        Optional<Module> theModule = moduleRepository.findById(theId);
        if (theModule.isPresent()) {
            List<Teacher> teachers = teacherRepository.findAll();
            theModel.addAttribute("module", theModule.get());
            theModel.addAttribute("teachers", teachers);
            return "modules/module-form-update";
        } else {
            return "redirect:/modules/list";
        }
    }

    @GetMapping("/showFormForReadonly")
    public String showFormForReadonly(@RequestParam("moduleId") int theId, Model theModel) {
        Optional<Module> theModule = moduleRepository.findById(theId);
        if (theModule.isPresent()) {
            theModel.addAttribute("module", theModule.get());
            return "modules/module-form-readonly";
        } else {
            return "redirect:/modules/list";
        }
    }
}
