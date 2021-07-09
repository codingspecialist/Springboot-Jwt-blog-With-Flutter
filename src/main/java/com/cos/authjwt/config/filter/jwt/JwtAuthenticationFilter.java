package com.cos.authjwt.config.filter.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.authjwt.domain.user.User;
import com.cos.authjwt.domain.user.UserRepository;
import com.cos.authjwt.web.dto.CMRespDto;
import com.cos.authjwt.web.dto.user.LoginReqDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;

// username, password  받아서 디비에서 확인해서 JWT 토큰 만들어서 응답해주는 친구
// /login + POST 요청일 때만 동작!!
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

	private final UserRepository userRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (!req.getMethod().equals("POST")) {
			resp.setContentType("text/plain; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("잘못된 요청입니다");
			out.flush();
			return;
		}

		System.out.println("로그인 인증 필터 JwtAuthenticationFilter 동작 시작");

		ObjectMapper om = new ObjectMapper();
		//om.registerModule(new JavaTimeModule());
		om.findAndRegisterModules();
		LoginReqDto loginReqDto = om.readValue(req.getInputStream(), LoginReqDto.class);
		System.out.println("다운 받은 데이터 : " + loginReqDto);
		// 2. DB (select) -> if(ssar, 1234) 체크 -> 원형(1, ssar, 1234, ssar@nate.com,
		// guest)
		//User userEntity = new User(1, "ssar", "1234", "ssar@nate.com", "GUEST");
		
		User principal =  userRepository.findByUsernameAndPassword(loginReqDto.getUsername(), loginReqDto.getPassword());
		
		if (principal == null) {
			resp.setContentType("text/plain; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("인증 되지 않았습니다. 다시 인증해주세요");
			out.flush();
			return;
		} else {
			String jwtToken = JWT.create().withSubject(JwtProps.SUBJECT)
					.withExpiresAt(new Date(System.currentTimeMillis() + JwtProps.EXPIRESAT))
					.withClaim("id", principal.getId())
					.sign(Algorithm.HMAC512(JwtProps.SECRET));

			// 헤더 키값 = RFC문서
			resp.setHeader(JwtProps.HEADER, JwtProps.AUTH +jwtToken);
			resp.setContentType("application/json; charset=utf-8");
			
			CMRespDto<User> cmRespDto = 
					new CMRespDto<User>(1, "success", principal);
			String cmRespDtoJson = om.writeValueAsString(cmRespDto);
			PrintWriter out = resp.getWriter();
			out.print(cmRespDtoJson); // CMRespDto
			out.flush();
		}

	}

}
