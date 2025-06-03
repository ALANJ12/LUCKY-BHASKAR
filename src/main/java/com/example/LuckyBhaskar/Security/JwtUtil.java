package com.example.LuckyBhaskar.Security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {


//    This line creates a random secret key used to sign the token
//     This key is like a password that only the server knows.
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);


     //The token will expire in 1 hour (in milliseconds).
    private final long EXPIRATION_TIME = 1000 * 60 * 60;


    //This method creates a JWT token with the username inside.
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("my-app")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

//    This method checks if the token is valid.
//    If valid, it returns the username inside.
//    If not valid (expired or modified), it returns null.
    public String validateTokenAndGetUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null; // invalid token
        }
    }



}
