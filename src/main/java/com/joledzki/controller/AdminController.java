//package com.joledzki.controller;
//
//import com.joledzki.authorities.Authorities;
//import com.joledzki.authorities.AuthoritiesRepository;
//import com.joledzki.authorities.AuthoritiesService;
//import com.joledzki.user.User;
//import com.joledzki.user.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//
//@Controller
//public class AdminController {
//
//    private UserRepository userRepository;
//    private AuthoritiesRepository authoritiesRepository;
//    private AuthoritiesService authoritiesService;
//
//    @Autowired
//    public AdminController(UserRepository userRepository, AuthoritiesRepository authoritiesRepository, AuthoritiesService authoritiesService){
//        this.userRepository = userRepository;
//        this.authoritiesRepository = authoritiesRepository;
//        this.authoritiesService = authoritiesService;
//    }
//
//    @GetMapping("/create-admin")
//    public String createAdmin(Model model){
//        model.addAttribute("user", new User());
//        return "createAdmin";
//    }
//
//    @PostMapping("/addAdmin")
//    public String addAdmin(User user, Model model){
//        Optional<User> userDB = userRepository.findByUsername(user.getUsername());
//
//        if(!userDB.isPresent()){
//            model.addAttribute("userError","User doesn't exist");
//        }
//        else{
//            List<Authorities> auth = new ArrayList<>();
//            auth.add(authoritiesRepository.findByName("ADMIN_ADD"));
//
//            userRepository.setUserAuthorities(userDB.get().getId(), auth);
//        }
//
//        return "createAdmin";
//    }
//
//}
