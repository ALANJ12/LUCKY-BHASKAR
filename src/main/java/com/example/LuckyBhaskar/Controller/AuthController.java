package com.example.LuckyBhaskar.Controller;

import com.example.LuckyBhaskar.Dto.LoginDto;
import com.example.LuckyBhaskar.Dto.UserDto;
import com.example.LuckyBhaskar.Security.JwtUtil;
import com.example.LuckyBhaskar.model.AuthRequest;
import com.example.LuckyBhaskar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private UserService userService;

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody UserDto request) {
        userService.registerUser(request);


        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/register/admin")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> registerAdmin(@RequestBody UserDto request) {
        userService.registerAdmin(request);
        return ResponseEntity.ok("Admin registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto request) {
        try {
            // Step 1: Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // Step 2: Generate token
            String token = jwtUtil.generateToken(request.getUsername());

            // Step 3: Return the token
            return ResponseEntity.ok().body(
                    java.util.Map.of("token", token)
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}