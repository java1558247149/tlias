package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtText {
    @Test
    public void testGenerateToken() {
        Map<String, Object> mdData = new HashMap<>();
        mdData.put("username", "kyrie");
        mdData.put("password", "123456");
        String kyrie = Jwts.builder().signWith(SignatureAlgorithm.HS256, "a3lyaWU=").addClaims(mdData).setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000)).compact();
        System.out.println(kyrie);
    }

    @Test
    public void testParseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsInVzZXJuYW1lIjoia3lyaWUiLCJleHAiOjE3NzgzMjQ4MzF9.c6E2wJCh2REBECuKo3MEovTBWeM95q2HxVoALagmqb8";
        Claims kyrie =Jwts.parser().setSigningKey("a3lyaWU=").parseClaimsJws(token).getBody();
        System.out.println(kyrie);
    }
}
