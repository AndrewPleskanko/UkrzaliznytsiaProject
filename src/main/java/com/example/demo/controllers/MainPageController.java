package com.example.demo.controllers;


import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
@AllArgsConstructor
public class MainPageController {

    final UserService userService;

    @GetMapping("/main-page")
    public String showMainPage() {
        return "mainPage";
    }

}
