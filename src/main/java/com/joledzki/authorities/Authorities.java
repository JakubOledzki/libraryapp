package com.joledzki.authorities;

import com.joledzki.user.User;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Authorities implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    public void setAuthority(String name){
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
