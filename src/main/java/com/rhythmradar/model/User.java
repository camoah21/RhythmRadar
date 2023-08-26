package com.rhythmradar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Column(name = "refresh_token")
    private String refreshToken;
    // add other fields like first name, last name, etc. if needed

    public void setRefreshToken(Object object) {
    }

    public Object getEmail() {
        return null;
    }

    public void setPassword(Object encodedNewPassword) {
    }

    public void setUsername(String username2) {
    }

    public void setEmail(Object email2) {
    }

    public void setRole(String string) {
    }
    
    // Standard getters and setters
}
