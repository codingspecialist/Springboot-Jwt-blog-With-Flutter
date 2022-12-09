package com.cos.authjwt.config.filter.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
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
import com.cos.authjwt.util.CustomLocalDateTimeSerializer;
import com.cos.authjwt.util.CustomResponseUtil;
import com.cos.authjwt.web.dto.CMRespDto;
import com.cos.authjwt.web.dto.user.LoginReqDto;
import com.cos.authjwt.web.dto.user.UserRespDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

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
		ObjectMapper om = new ObjectMapper();

		if (!req.getMethod().equals("POST")) {
			try {
				CMRespDto<User> cmRespDto = new CMRespDto<User>(-1, "Post로 요청해주세요", null);
				CustomResponseUtil.response(resp, cmRespDto);
				return;
			} catch (Exception e) {
				System.out.println("파싱 실패 :" + e.getMessage());
			}
		}

		System.out.println("로그인 인증 필터 JwtAuthenticationFilter 동작 시작");

		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
		om.registerModule(simpleModule);

		LoginReqDto loginReqDto = om.readValue(req.getInputStream(), LoginReqDto.class);
		System.out.println("다운 받은 데이터 : " + loginReqDto);

		User principal = userRepository.findByUsernameAndPassword(loginReqDto.getUsername(), loginReqDto.getPassword());

		if (principal == null) {
			try {
				CMRespDto<User> cmRespDto = new CMRespDto<User>(-1, "인증되지 않았습니다", null);
				CustomResponseUtil.response(resp, cmRespDto);
				return;
			} catch (Exception e) {
				System.out.println("파싱 실패 :" + e.getMessage());
			}
			return;
		} else {
			String jwtToken = JwtProcess.create(principal.getId());

			// 헤더 키값 = RFC문서
			resp.setHeader(JwtProps.HEADER, JwtProps.AUTH + jwtToken);
			principal.setPassword(null);

			try {
				CMRespDto<UserRespDto> cmRespDto = new CMRespDto<>(1, "성공", new UserRespDto(principal));
				CustomResponseUtil.response(resp, cmRespDto);
				return;
			} catch (Exception e) {
				System.out.println("파싱 실패 :" + e.getMessage());
			}
		}

	}

}
