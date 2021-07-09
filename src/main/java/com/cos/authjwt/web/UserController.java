package com.cos.authjwt.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.authjwt.config.auth.LoginUser;
import com.cos.authjwt.domain.user.User;
import com.cos.authjwt.service.UserService;
import com.cos.authjwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController // 데이터만 리턴한다.
public class UserController {
	
	private final UserService userService;
	
	// Post : /login (Json) - 이건 JwtAutenticationFilter 에서!!
	
	@GetMapping("/join")
	public CMRespDto<?> join(@RequestBody User user){
		User userEntity = userService.회원가입(user);
		return new CMRespDto<>(1, "회원가입완료", userEntity);
	}
	
	// @LoginUser로 세션정보 접근 가능!!
	@GetMapping("/user/{id}")
	public CMRespDto<?> userinfo(@PathVariable Integer id, @LoginUser User user){
		
		User userEntity = userService.회원정보보기(id);
		
		if(user != null) {
			return new CMRespDto<>(1, "회원정보확인완료", userEntity);
		}else {
			return new CMRespDto<>(-1, "로그인 되지 않았습니다.", null);
		}
		
	}

}
