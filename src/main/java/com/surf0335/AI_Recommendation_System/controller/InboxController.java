package com.surf0335.AI_Recommendation_System.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InboxController {
    @GetMapping("/inbox")
    public String getinboxPage(Model m){
        return "Inbox";
    }
}
