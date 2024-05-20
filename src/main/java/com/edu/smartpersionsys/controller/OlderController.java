package com.edu.smartpersionsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OlderController {
    @GetMapping("olderPage")
    public String olderPage(Model model){
        model.addAttribute("userName","张三");
        return "olderpage";
    }
}
