package com.pratesdev.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void shouldGenerateAndValidateJwtToken() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);

        assertNotNull(token, "Token should not be null");
        assertEquals(username, jwtUtil.extractUsername(token), "Extracted username should match");
    }

    @Test
    void shouldThrowExceptionForExpiredToken() {
        JwtUtil jwtUtilWithShortExpiry = new JwtUtil(1000); // 1-second expiry for test
        String token = jwtUtilWithShortExpiry.generateToken("testuser");

        assertThrows(io.jsonwebtoken.ExpiredJwtException.class, () -> {
            Thread.sleep(2000);
            jwtUtilWithShortExpiry.extractUsername(token);
        });
    }
}
