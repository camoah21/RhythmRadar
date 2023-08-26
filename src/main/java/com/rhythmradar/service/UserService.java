package com.rhythmradar.service;

import com.rhythmradar.model.User;
import com.rhythmradar.repository.UserRepository;
import com.rhythmradar.dto.UserRegistrationDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(UserRegistrationDto registrationDto) throws Exception {
        if(userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }
        
        if(userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new Exception("Email already in use");
        }
        
        User newUser = new User();
        newUser.setUsername(registrationDto.getUsername());
        newUser.setEmail(registrationDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        
        // Assuming a default role for simplicity
        newUser.setRole("USER");

        try {
            return userRepository.save(newUser);
        } catch(DataIntegrityViolationException e) {
            // Handle the constraint violation in more detail if needed
            throw new Exception("Error registering user", e);
        }
    }

    public Map<String, String> generateTokens(User user) {
        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);
    
        // Store refreshToken in the database
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
    
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        return tokens;
    }

    private String generateRefreshToken(User user) {
        return null;
    }

    private String generateAccessToken(User user) {
        return null;
    }

    public User findByRefreshToken(String providedRefreshToken) {
        return null;
    }

    public User findByUsername(String name) {
        return null;
    }

    public void saveUser(User user) {
    }

    public User findByEmail(String email) {
        return null;
    }

    public void createPasswordResetTokenForUser(User user, String token) {
    }
    

    // other service methods
}


