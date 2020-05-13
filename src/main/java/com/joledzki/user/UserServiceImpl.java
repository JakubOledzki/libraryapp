package com.joledzki.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserDetails(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean checkUser = userRepository.findByUsername(username).isPresent();
        if(checkUser){
//            System.out.println("Logged with: "+ userRepository.findByUsername(username).get().getAuthorities());
            return  userRepository.findByUsername(username).get();
        }
        else{
            throw new UsernameNotFoundException("USER NOT EXISTS");
        }
    }
}
