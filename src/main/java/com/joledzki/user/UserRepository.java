package com.joledzki.user;

import com.joledzki.authorities.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByIdAndAuthorities(Long id, Authorities auth);
}
