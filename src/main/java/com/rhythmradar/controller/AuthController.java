package com.rhythmradar.controller;

import com.rhythmradar.dto.UserRegistrationDto;
import com.rhythmradar.dto.LoginRequestDto;
import com.rhythmradar.model.User;
import com.rhythmradar.service.UserService;
import com.rhythmradar.repository.TokenBlacklistRepository;
import com.rhythmradar.service.JwtTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtService;

    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        try {
            userService.register(registrationDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(userDetails);

        // Set JWT as HttpOnly cookie
        Cookie jwtCookie = new Cookie("JWT-TOKEN", jwtToken);
        jwtCookie.setHttpOnly(true);
        response.addCookie(jwtCookie);

        return ResponseEntity.ok("Logged in successfully");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> payload) {
        String providedRefreshToken = payload.get("refresh_token");
        User user = userService.findByRefreshToken(providedRefreshToken); // Assuming this method exists

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        String newAccessToken = jwtService.generateToken(user); // Assuming this is the logic
        return ResponseEntity.ok(Collections.singletonMap("access_token", newAccessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(Principal principal, @RequestHeader(value="Authorization", required=false) String token) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName()); // Assuming this method exists

            if (user != null) {
                user.setRefreshToken(null);
                userService.saveUser(user); // Assuming this method exists
            }
        }

        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
            tokenBlacklistRepository.addToBlacklist(token);
        }
        
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        // Further logic...
        return ResponseEntity.ok().body("Password reset link sent to email!");
    }

}
