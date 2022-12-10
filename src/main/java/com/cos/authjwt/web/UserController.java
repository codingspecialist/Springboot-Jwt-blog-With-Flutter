package com.cos.authjwt.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.authjwt.config.auth.LoginUser;
import com.cos.authjwt.config.filter.jwt.JwtProcess;
import com.cos.authjwt.config.filter.jwt.JwtProps;
import com.cos.authjwt.domain.user.User;
import com.cos.authjwt.domain.user.UserRepository;
import com.cos.authjwt.handler.ex.CustomApiException;
import com.cos.authjwt.service.UserService;
import com.cos.authjwt.web.dto.CMRespDto;
import com.cos.authjwt.web.dto.user.UserRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController // 데이터만 리턴한다.
public class UserController {

	private final UserService userService;
	private final UserRepository userRepository;

	@GetMapping("/jwtToken")
	public CMRespDto<?> jwtToken(HttpServletRequest request) {
		String jwtToken = request.getHeader("authorization");
		if (jwtToken == null) {
			throw new CustomApiException("토큰이 헤더에 없습니다.");
		}
		System.out.println("토큰이 헤더 있습니다.");
		jwtToken = jwtToken.replace(JwtProps.AUTH, "");
		int userId = JwtProcess.verify(jwtToken);
		User userEntity = userRepository.findById(userId).orElseThrow(() -> new CustomApiException("토큰 검증 실패"));
		return new CMRespDto<>(1, "회원정보전달완료", new UserRespDto(userEntity));
	}

	// Post : /login (Json) - 이건 JwtAutenticationFilter 에서!!
	@PostMapping("/join")
	public CMRespDto<?> join(@RequestBody User user) {
		System.out.println("나실행됨 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		User userEntity = userService.회원가입(user);
		userEntity.setPassword(null); // password는 돌려주지 않는다. (컨트롤러에서 변경했기 때문에 dirty checking 안됨)
		return new CMRespDto<>(1, "회원가입완료", new UserRespDto(userEntity));
	}

	// @LoginUser로 세션정보 접근 가능!!
	@GetMapping("/user/{id}")
	public CMRespDto<?> userinfo(@PathVariable Integer id, @LoginUser User principal) {

		if (principal.getId() == id) {
			User userEntity = userService.회원정보보기(id);
			return new CMRespDto<>(1, "회원정보확인완료", new UserRespDto(userEntity));
		} else {
			return new CMRespDto<>(-1, "권한이 없습니다.", null);
		}

	}

	// Test 용 (실제 앱에서 사용안함)
	@PutMapping("/user/{id}")
	public CMRespDto<?> userUpdate(@PathVariable Integer id, @RequestBody User user, @LoginUser User principal) {

		if (principal.getId() == id) {
			User userEntity = userService.회원수정(id, user);
			return new CMRespDto<>(1, "회원정보수정완료", new UserRespDto(userEntity));
		} else {
			return new CMRespDto<>(-1, "권한이 없습니다.", null);
		}

	}

	@GetMapping("/init/user")
	public CMRespDto<?> initUser() {
		List<User> users = userService.회원목록보기();
		List<UserRespDto> dtos = users.stream().map(UserRespDto::new).collect(Collectors.toList());
		return new CMRespDto<>(1, "목록보기완료", dtos);
	}

}
