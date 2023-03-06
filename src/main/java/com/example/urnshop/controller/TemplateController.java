package com.example.urnshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping(value="login")
    public String getLoginView() {
        return "login";
    }
    
}
