package com.cos.authjwt.config.filter.jwt;

public interface JwtProps {
	public static final String SUBJECT = "cos토큰";
	public static final String SECRET = "부산it";
	public static final String AUTH = "Bearer ";
	public static final String HEADER = "Authorization";
	public static final Integer EXPIRESAT = 1000 * 60 * 60 * 24 * 10; // 10일
}
