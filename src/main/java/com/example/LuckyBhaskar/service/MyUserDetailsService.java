package com.example.LuckyBhaskar.service;

import com.example.LuckyBhaskar.model.Users;
import com.example.LuckyBhaskar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Hard-coded user. Replace with DB logic if needed
//        if (username.equals("john")) {
//            return new User(
//                    "john",
//                    "password",  // Plain text
//                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
//            );
//        } else {
//            throw new UsernameNotFoundException("User not found");
//        }
//    }
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}

