package com.joledzki.controller;

import com.joledzki.user.User;
import com.joledzki.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping("/hello")
    public String getHello(){
        return "hello";
    }

    @RequestMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("accounts", userRepository.findAll());
        return "login";
    }


}
