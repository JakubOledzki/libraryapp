package com.joledzki.controller;

import com.joledzki.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilController {

    private UserServiceImpl userService;

    @Autowired
    public ProfilController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String getProfil(Model model){
        model.addAttribute("user",userService.getUserDetails());
        return "index";
    }

}
