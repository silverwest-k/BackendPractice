package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MainController {

    @GetMapping(value = "/{name}")
    public String showMain(Model model, @PathVariable("name")String name) {
        model.addAttribute("name", name);
        return "Main";
    }
}
