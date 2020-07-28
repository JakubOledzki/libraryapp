package com.joledzki.user;

import com.joledzki.authorities.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.GrantedAuthority;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByIdAndAuthorities(Long id, Authorities auth);

    Optional<User> findByUsernameAndPassword(String username, String password);


//    @Modifying
//    @Transactional
//    @Query("UPDATE User u SET u.authorities =:authorities WHERE u.id=:id")
//    void setUserAuthorities(@Param("id") Long id, @Param("authorities") Collection<? extends GrantedAuthority> authorities);

}
