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
        http.authorizeRequests().antMatchers("/","/index","/books/{id}").authenticated()
                                .antMatchers("/books").hasAnyAuthority("USER_READ")
                                .antMatchers("/book/rent/{id}","/book/back/{id}").hasAnyAuthority("USER_RENT")
                                .antMatchers("/addBook", "/book/create").hasAnyAuthority("ADMIN_ADD")
                                .antMatchers("/book/edit/{id}","/initEditBook").hasAnyAuthority("ADMIN_EDIT")
                                .antMatchers("/book/delete/{id}").hasAnyAuthority("ADMIN_DELETE")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/index")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                .and()
                .logout()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
