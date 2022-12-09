package com.cos.authjwt.config.filter.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtProcess {
    public static String create(int userId) {
        String jwtToken = JWT.create().withSubject(JwtProps.SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProps.EXPIRESAT))
                .withClaim("id", userId)
                .sign(Algorithm.HMAC512(JwtProps.SECRET));
        return jwtToken;
    }

    public static int verify(String jwtToken) {
        DecodedJWT decodeJwt = JWT.require(Algorithm.HMAC512(JwtProps.SECRET)).build().verify(jwtToken);
        Integer userId = decodeJwt.getClaim("id").asInt();
        return userId;
    }
}
