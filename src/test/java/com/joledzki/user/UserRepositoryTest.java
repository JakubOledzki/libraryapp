package com.joledzki.user;

import com.joledzki.authorities.Authorities;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByUsername_thenReturnUser(){
        //given
        User user = new User("user69","password","Jan","Kowalski",null);
        entityManager.persist(user);
        entityManager.flush();

        //when
        User userFound = userRepository.findByUsername("user69").get();

        //then
        assertThat(userFound.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void whenFindByUsername_thenReturnOptionalEmptyUser(){
        //when
        Optional<User> userFound = userRepository.findByUsername("user69");

        //then
        assertThat(userFound.isPresent()).isEqualTo(false);
    }

}