package com.joledzki.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityPassword {

    @Bean
    public PasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

}
