package com.cos.authjwt.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.authjwt.domain.user.User;
import com.cos.authjwt.domain.user.UserRepository;
import com.cos.authjwt.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	@Transactional
	public User 회원가입(User user) {
		return userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	public User 회원정보보기(Integer id) {
		return userRepository.findById(id).orElseThrow(
				()-> new CustomApiException("해당 "+id+"는 존재하지 않습니다.")
		);
	}
}
