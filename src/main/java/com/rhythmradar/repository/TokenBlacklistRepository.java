package com.rhythmradar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TokenBlacklistRepository {

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public TokenBlacklistRepository(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addToBlacklist(String token) {
        
    }

    public boolean isBlacklisted(String token) {
        return redisTemplate.hasKey(token);
    }
}

