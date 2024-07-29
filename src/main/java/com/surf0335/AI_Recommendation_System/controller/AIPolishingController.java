package com.surf0335.AI_Recommendation_System.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AIPolishingController {
    //@Autowired
    //private AIPolishService polishService;

    @GetMapping("/ai_polish")
    public String getAiPolishPage(Model m){
        return "AiPolishing";
    }

}
