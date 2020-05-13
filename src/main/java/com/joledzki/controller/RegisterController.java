package com.joledzki.controller;

import com.joledzki.authorities.Authorities;
import com.joledzki.authorities.AuthoritiesRepository;
import com.joledzki.config.SecurityPassword;
import com.joledzki.user.User;
import com.joledzki.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class RegisterController {

    private UserRepository userRepository;
    private AuthoritiesRepository authoritiesRepository;
    private SecurityPassword securityPassword;

    @Autowired
    public RegisterController(UserRepository userRepository, AuthoritiesRepository authoritiesRepository, SecurityPassword securityPassword){
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.securityPassword = securityPassword;
    }

    @RequestMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/add-user")
    public String addUser(User user){
        List<Authorities> auth = new ArrayList<>();
        auth.add(authoritiesRepository.findByName("USER_READ"));
        auth.add(authoritiesRepository.findByName("USER_RENT"));
        user.setPassword(securityPassword.encode().encode(user.getPassword()));
        user.setAuthorities(auth);
        userRepository.save(user);
        return "register";
    }

}
