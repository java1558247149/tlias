package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 * 适配版本: io.jsonwebtoken:jjwt:0.9.1
 */
public class JwtsUtil {

    // 私密密钥，与您的单元测试保持一致
    private static final String SECRET_KEY = "a3lyaWU=";

    /**
     * 生成JWT令牌
     * <p>
     * 默认有效期：10分钟
     *
     * @param claims 需要在令牌中携带的自定义数据（如用户ID、用户名等）
     * @return 生成的JWT字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        // 默认10分钟有效期
        return generateToken(claims, 10L * 60 * 1000);
    }

    /**
     * 生成JWT令牌
     *
     * @param claims       需要在令牌中携带的自定义数据
     * @param expireMillis 令牌的有效期，单位为毫秒
     * @return 生成的JWT字符串
     */
    public static String generateToken(Map<String, Object> claims, long expireMillis) {
        return Jwts.builder()
                // 设置签名算法和密钥
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // 添加自定义声明
                .addClaims(claims)
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expireMillis))
                // 生成紧凑的JWT字符串
                .compact();
    }

    /**
     * 校验并解析JWT令牌
     *
     * @param token 待校验的JWT字符串
     * @return 解析后的Claims对象；如果令牌无效或已过期，则返回null
     */
    public static Claims parseToken(String token) {

        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
