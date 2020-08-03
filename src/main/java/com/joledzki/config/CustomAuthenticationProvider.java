package com.joledzki.config;

import com.joledzki.user.User;
import com.joledzki.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityPassword securityPassword;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent() && securityPassword.encode().matches(password, user.get().getPassword())){
            System.out.println("Logged: "+username);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.get().getUsername(),user.get().getPassword(),user.get().getAuthorities());
            token.setDetails(user.get());
            return token;
        }
        else return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
