package com.joledzki.controller;

import com.joledzki.user.User;
import com.joledzki.user.UserRepository;
import com.joledzki.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/login")
    public String getLogin(Model model){
        return "login";
    }


}
