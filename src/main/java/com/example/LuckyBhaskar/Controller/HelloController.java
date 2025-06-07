package com.example.LuckyBhaskar.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
//    @PreAuthorize("permitAll()")
    public String hello( Authentication authentication) {
        String name = authentication.getName();
        return "Hello welcome "+name;
    }}