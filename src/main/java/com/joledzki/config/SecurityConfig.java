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

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    private SecurityPassword securityPassword;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/index","/myBooks","/test").authenticated()
                                .antMatchers("/list-books").hasAnyAuthority("USER_READ")
                                .antMatchers("/rentBook","/giveBookBack").hasAnyAuthority("USER_RENT")
                                .antMatchers("/addBook", "/create-book").hasAnyAuthority("ADMIN_ADD")
                                .antMatchers("/editBook","/initEditBook").hasAnyAuthority("ADMIN_EDIT")
                                .antMatchers("/deleteBook").hasAnyAuthority("ADMIN_DELETE")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/test")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                .and()
                .logout()
                .and()
                .csrf().disable();
    }
}
