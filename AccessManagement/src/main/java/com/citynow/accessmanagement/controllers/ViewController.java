package com.citynow.accessmanagement.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
public class ViewController {

    @RequestMapping("index")
    public String viewIndex(){
        return "index";
    }

}
