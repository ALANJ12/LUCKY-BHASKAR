package com.example.LuckyBhaskar.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
//    @PreAuthorize("permitAll()")
    public String hello() {
        return "Hello! You're authenticated!";
    }}