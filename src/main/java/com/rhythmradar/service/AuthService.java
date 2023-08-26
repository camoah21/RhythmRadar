package com.rhythmradar.service;

import com.rhythmradar.model.User;
import com.rhythmradar.repository.UserRepository;
import com.rhythmradar.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;  // Assuming you have a service for email tasks

    private Object encodedNewPassword;

    public void processForgotPassword(String email) {
        User user = userService.findByEmail(email);  // Assuming you have a method to find user by email
        if (user != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            mailService.sendPasswordResetEmail(user.getEmail(), token);
        }
        // Consider adding logic for handling cases where email is not registered, etc.
    }

    public void resetPassword(String token, String newPassword) {
        // Logic to verify the token and find the associated user

        User user = UserRepository.findByResetToken(token);
        if(user != null) {
            // Logic to update user's password
            user.setPassword(encodedNewPassword); // ensure password is encoded
            userRepository.save(user);

            // Notify user of password change
            mailService.sendPasswordChangeNotification(user.getEmail());
        } else {
            // Handle invalid token
        }
    }

    // ... Other authentication and authorization related methods ...
}

