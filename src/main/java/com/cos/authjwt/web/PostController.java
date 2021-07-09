package com.cos.authjwt.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RestController;

import com.cos.authjwt.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {
	
	private final HttpSession session;
	private final UserRepository userRepository;

}
