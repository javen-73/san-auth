package com.javen.sanauth.commons.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    public static String sign(){
        String token = Jwts.builder()
                .setSubject("admin")
                .signWith(SignatureAlgorithm.HS512, "xiaoti")
                .compact();
        return token;
    }

    public static void main(String[] args) {
        String sign = sign();
        System.out.println(sign);
    }
}
