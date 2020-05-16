package com.joledzki.authorities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
    Authorities findByName(String name);

}
