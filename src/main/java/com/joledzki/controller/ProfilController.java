package com.joledzki.controller;

import com.joledzki.authorities.Authorities;
import com.joledzki.authorities.AuthoritiesRepository;
import com.joledzki.authorities.AuthoritiesService;
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

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @GetMapping("/")
    public String getProfil(Model model){
        User user = userService.getUserDetails();
        System.out.println(userService.getUserDetails().getAuthorities());
        model.addAttribute("user",user);
        model.addAttribute("authorities", authoritiesService.getAuthoritiesNameForUser(user.getAuthorities()));
        return "index";
    }

    @PostMapping("/index")
    public String getIndex(Model model){
        User user = userService.getUserDetails();
        model.addAttribute("user",user);
        model.addAttribute("authorities", authoritiesService.getAuthoritiesNameForUser(user.getAuthorities()));
        return "index";
    }

}
