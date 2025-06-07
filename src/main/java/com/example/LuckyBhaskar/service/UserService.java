package com.example.LuckyBhaskar.service;

import com.example.LuckyBhaskar.Dto.UserDto;
import com.example.LuckyBhaskar.model.Users;
import com.example.LuckyBhaskar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserDto request) {
        Users user = new Users();



        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // BCrypt
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole("ROLE_USER");
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    public void registerAdmin(UserDto request) {
        Users admin = new Users();
        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setName(request.getName());
        admin.setEmail(request.getEmail());
        admin.setRole("ROLE_ADMIN");
        admin.setCreatedAt(LocalDateTime.now());

        userRepository.save(admin);
    }
}
