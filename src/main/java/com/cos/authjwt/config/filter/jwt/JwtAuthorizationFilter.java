package com.cos.authjwt.config.filter.jwt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cos.authjwt.domain.user.User;
import com.cos.authjwt.domain.user.UserRepository;
import com.cos.authjwt.handler.ex.CustomApiException;
import com.cos.authjwt.util.CustomResponseUtil;
import com.cos.authjwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

// /post/**, /user/**,
@RequiredArgsConstructor
public class JwtAuthorizationFilter implements Filter {

	private final UserRepository userRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("사용자 인가 필터 JwtAuthorizationFilter 동작 시작");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String jwtToken = req.getHeader(JwtProps.HEADER);

		System.out.println("토큰 : " + jwtToken);

		if (jwtToken == null) {
			try {
				CMRespDto<User> cmRespDto = new CMRespDto<User>(-1, "JWT 토큰이 없습니다", null);
				CustomResponseUtil.response(resp, cmRespDto);
				return;
			} catch (Exception e) {
				System.out.println("파싱 실패 :" + e.getMessage());
			}
		} else {
			jwtToken = jwtToken.replace(JwtProps.AUTH, "");
			System.out.println("변경된 토큰 : " + jwtToken);
			try {
				int userId = JwtProcess.verify(jwtToken);
				User principal = userRepository.findById(userId).orElseThrow(
						() -> new CustomApiException("해당 유저 아이디 " + userId + "는 존재하지 않습니다"));

				System.out.println("인가 필터 : principal : " + principal);
				HttpSession session = req.getSession();
				session.setAttribute("principal", principal);
				chain.doFilter(req, resp); // 다시 체인을 타게 해야 한다.
			} catch (Exception e1) {
				try {
					CMRespDto<User> cmRespDto = new CMRespDto<User>(-1, "JWT 토큰 검증 실패", null);
					CustomResponseUtil.response(resp, cmRespDto);
					return;
				} catch (Exception e) {
					System.out.println("파싱 실패 :" + e.getMessage());
				}
			}

		}
	}

}
