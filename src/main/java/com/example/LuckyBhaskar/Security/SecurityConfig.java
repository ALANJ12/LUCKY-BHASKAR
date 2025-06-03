package com.example.LuckyBhaskar.Security;

import com.example.LuckyBhaskar.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
//@Configuration tells Spring: "This class provides some configurations (Java-based)."
//
//@EnableMethodSecurity allows you to use @PreAuthorize, @Secured, etc., on methods. Not required for basic JWT, but useful if you want to protect methods (like @PreAuthorize("hasRole('ADMIN')")).
public class SecurityConfig {

//    These are two objects Spring will inject into this config:
//    JwtAuthFilter: custom filter that checks the JWT in each request.
//        MyUserDetailsService: knows how to load users (in our example, hardcoded "john").
    private final JwtAuthFilter jwtAuthFilter;
    private final MyUserDetailsService userDetailsService;

//    This is a constructor that receives the two beans and stores them in the fields above.
//    Spring does dependency injection automatically here.
    public SecurityConfig(JwtAuthFilter jwtAuthFilter, MyUserDetailsService userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }


//    This method tells Spring: "Here’s how I want to configure security."
//    SecurityFilterChain is how Spring Security applies filters to requests (like JWT checking, login, etc)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http  //Start chaining Spring Security configurations.
                .csrf(csrf -> csrf.disable()) // ✅ New way to disable CSRF //
                //This disables CSRF protection, which is required for stateless APIs
                //CSRF is only for browser-based forms; you don’t need it in JWT APIs.
                .authorizeHttpRequests(auth -> auth //This begins the block where we define rules for different HTTP requests (who is allowed to access what).
//
//     This says: "Let everyone (unauthenticated) access /auth/login."
//        This is necessary so users can log in without a token.
                        .requestMatchers("/auth/login").permitAll()

//                        All other endpoints (like /hello) must be authenticated.
//                                Meaning: only users with a valid JWT can access them.
                        .anyRequest().authenticated()
                )
//        This adds our custom JWT filter before the built-in username/password login filter.
//        Why before? So we can intercept requests early, check the token, and authenticate manually.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();//returns the security configuration
    }


//    This exposes the AuthenticationManager bean.
//    It lets us authenticate manually in the login controller like this:
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
//    This defines how passwords are checked.
//    NoOpPasswordEncoder = plain text passwords (not safe in production).
//    Later you can replace this with BCryptPasswordEncoder for hashing.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // for plain-text (use BCrypt in production)
    }


}
