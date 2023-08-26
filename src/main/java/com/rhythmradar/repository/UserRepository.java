package com.rhythmradar.repository;

import com.rhythmradar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    static User findByResetToken(String token) {
        return null;
    }

    Optional<User> findByEmail(Object email);
}

