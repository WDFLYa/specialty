package nuc.edu.cn.specialtyweb.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    private static final String SECRET_KEY = "N2ZlYjYyYmY1NjQxZDgxNjIyNjIyNjJhMzQ0NjE3ZDQ1NzI3NjE0YTQ1ZDMyYmEyZjYyNmYxMTYzN2Y2MjRiMTc0N2ZmYmM4ZDQzZDA0Nw==\n";

    // 生成 token（有效期：1 天）
    public static String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // 解析 token 获取用户 ID
    public static Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
}
