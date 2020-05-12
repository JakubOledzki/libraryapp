package com.joledzki.controller;

import com.joledzki.config.SecurityPassword;
import com.joledzki.user.User;
import com.joledzki.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    private UserRepository userRepository;
    private SecurityPassword securityPassword;

    @Autowired
    public RegisterController(UserRepository userRepository, SecurityPassword securityPassword){
        this.userRepository = userRepository;
        this.securityPassword = securityPassword;
    }

    @RequestMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/add-user")
    public String addUser(User user){
        user.setPassword(securityPassword.encode().encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "register";
    }

}
