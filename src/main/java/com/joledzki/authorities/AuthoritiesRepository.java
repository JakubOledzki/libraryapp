package com.joledzki.authorities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
    Authorities findByName(String name);

}
