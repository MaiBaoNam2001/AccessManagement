package com.mbn.accessmanagement.controllers;

import com.mbn.accessmanagement.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }
}
