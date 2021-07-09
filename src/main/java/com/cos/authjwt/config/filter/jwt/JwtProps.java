package com.cos.authjwt.config.filter.jwt;

interface JwtProps {
	public static final String SUBJECT = "cos토큰";
	public static final String SECRET = "부산it";
	public static final String AUTH = "Bearer ";
	public static final String HEADER = "Authorization";
	public static final Long EXPIRESAT = 10*60*60L;
}
