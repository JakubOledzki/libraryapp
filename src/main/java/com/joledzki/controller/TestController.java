package com.joledzki.controller;

import com.joledzki.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class TestController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/test")
    public String getTest(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        System.out.println("Username: "+ userService.getUserDetails().getUsername());
        return "test";
    }

}
