package com.example.authservice.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

    private final RedisTemplate<String , String> redisTemplate;

    public void save(String token, String username) {
        redisTemplate.opsForValue().set("refresh:" + token, username, 7, TimeUnit.DAYS);
    }

    public boolean exists(String token, String username) {
        String val = redisTemplate.opsForValue().get("refresh:" + token);
        return username.equals(val);
    }

    public void delete(String token) {
        redisTemplate.delete("refresh:" + token);
    }
}
