package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class Home {
    
    @GetMapping("/home")
    public String home(Model model) {
        String name = "John Doe";
        model.addAttribute("name", name);
        return "index";
    }
    
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}