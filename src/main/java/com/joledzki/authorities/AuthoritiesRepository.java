package com.joledzki.authorities;

import com.joledzki.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

    Authorities findByName(String name);

    List<Authorities> findAllByUsers(User user);

}
