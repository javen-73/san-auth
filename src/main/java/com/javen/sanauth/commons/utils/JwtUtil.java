package com.javen.sanauth.commons.utils;

import com.javen.sanauth.auth.constants.ShiroConstant;
import com.javen.sanauth.auth.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    public static String sign(User user){
        String token = Jwts.builder()
                .setId(user.getId())
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, ShiroConstant.ENCRYPTOR)
                .compact();
        return token;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId("1");
        user.setUsername("admin");
        String sign = sign(user);
        String parse = parse(sign);
        System.out.println(parse);
    }

    public static String parse(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(ShiroConstant.ENCRYPTOR)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
