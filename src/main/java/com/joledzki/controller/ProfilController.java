package com.joledzki.controller;

import com.joledzki.authorities.Authorities;
import com.joledzki.authorities.AuthoritiesRepository;
import com.joledzki.user.User;
import com.joledzki.user.UserRepository;
import com.joledzki.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProfilController {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    public ProfilController(UserServiceImpl userService, UserRepository userRepository, AuthoritiesRepository authoritiesRepository){
        this.userService = userService;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    @GetMapping("/")
    public String getProfil(Model model){
        User user = userService.getUserDetails();
        Authorities auth = authoritiesRepository.findByName("ADMIN_ADD");
        model.addAttribute("user",user);
        model.addAttribute("admin_add",userRepository.findByIdAndAuthorities(user.getId(), auth).isPresent());
        return "index";
    }

    @PostMapping("/index")
    public String getIndex(Model model){
        User user = userService.getUserDetails();
        Authorities auth = authoritiesRepository.findByName("ADMIN_ADD");
        model.addAttribute("user",user);
        model.addAttribute("admin_add",userRepository.findByIdAndAuthorities(user.getId(), auth).isPresent());
        return "index";
    }

}
