package com.rhythmradar.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.rhythmradar.model.User;

import java.util.Date;

@Service
public class JwtTokenService {

    @Value("${jwt.secret}")
    private String SECRET;

    public String generateToken(String subject) {
        int expirationTimeInMilliseconds = 3600000; // for example, 1 hour
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTimeInMilliseconds);

        String jwt = Jwts.builder()
            .setSubject(subject) // typically the user's username or id
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();

        return jwt;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            // token is valid and not expired
            return true;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            // token has expired
            return false;
        } catch (io.jsonwebtoken.JwtException e) {
            // token is invalid
            return false;
        }
    }

    public String generateToken(UserDetails userDetails) {
        return null;
    }

    public String generateToken(User user) {
        return null;
    }
    



    // You can add other methods related to JWT here, like token verification, etc.
}

