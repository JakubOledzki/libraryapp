package com.joledzki.authorities;

import com.joledzki.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class Authorities implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    private Set<User> users;

    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
