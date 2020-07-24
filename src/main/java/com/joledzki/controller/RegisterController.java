package com.joledzki.controller;

import com.joledzki.authorities.Authorities;
import com.joledzki.authorities.AuthoritiesRepository;
import com.joledzki.config.SecurityPassword;
import com.joledzki.user.User;
import com.joledzki.user.UserRepository;
import com.joledzki.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;
    @Autowired
    private SecurityPassword securityPassword;

    @RequestMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/add-user")
    @Validated()
    public String addUser(@Valid User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "register";
        }

        List<Authorities> auth = new ArrayList<>();
        auth.add(authoritiesRepository.findByName("USER_READ"));
        auth.add(authoritiesRepository.findByName("USER_RENT"));
        user.setPassword(securityPassword.encode().encode(user.getPassword()));
        user.setAuthorities(auth);
        userRepository.save(user);
        return "login";
    }

}
