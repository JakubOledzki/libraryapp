package com.joledzki.config;

import com.joledzki.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    UserServiceImpl userService;
    SecurityPassword securityPassword;

    @Autowired
    public SecurityConfig(UserServiceImpl userService, SecurityPassword securityPassword){
        this.userService = userService;
        this.securityPassword = securityPassword;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(securityPassword.encode());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/hello").authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/hello")
                    .failureForwardUrl("/login?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                .and()
                .logout()
                .and()
                .csrf().disable();
    }
}
