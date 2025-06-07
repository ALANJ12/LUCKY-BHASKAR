package com.example.LuckyBhaskar.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "users", schema = "bhaskar")
public class Users {



//        public User(String username, String password, String name, String email, String role, LocalDateTime createdAt) {
//            this.username = username;
//            this.password = password;
//            this.name = name;
//            this.email = email;
//            this.role = role;
//            this.createdAt = createdAt;
//        }
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        @Column(nullable = false, unique = true)
        private String username;

        private String password;
        private String name;
//
//        public User() {
//        }

        @Column(nullable = false, unique = true)
        private String email;

        private String role;
        private LocalDateTime createdAt = LocalDateTime.now();

        // Getters and setters...

}
