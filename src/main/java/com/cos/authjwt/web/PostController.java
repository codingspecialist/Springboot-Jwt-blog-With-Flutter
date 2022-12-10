package com.cos.authjwt.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.authjwt.config.auth.LoginUser;
import com.cos.authjwt.domain.post.Post;
import com.cos.authjwt.domain.user.User;
import com.cos.authjwt.service.PostService;
import com.cos.authjwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {

	private final PostService postService;

	@GetMapping("/post")
	public CMRespDto<?> findAll() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new CMRespDto<>(1, "목록보기완료", postService.게시글목록보기());
	}

	@GetMapping("/post/{id}")
	public CMRespDto<?> findById(@PathVariable Integer id) {
		return new CMRespDto<>(1, "상세보기완료", postService.게시글상세보기(id));
	}

	@PostMapping("/post")
	public CMRespDto<?> save(@LoginUser User principal, @RequestBody Post post) {
		System.out.println("세션값 : " + principal);
		post.setUser(principal);
		return new CMRespDto<>(1, "글쓰기완료", postService.게시글쓰기(post));
	}

	@PutMapping("/post/{id}")
	public CMRespDto<?> update(@PathVariable Integer id, @RequestBody Post post, @LoginUser User principal) {

		Post postEntity = postService.게시글상세보기(id);
		if (principal.getId() == postEntity.getUser().getId()) {
			return new CMRespDto<>(1, "수정하기완료", postService.게시글수정하기(id, post));
		} else {
			return new CMRespDto<>(-1, "수정실패 권한없음", null);
		}
	}

	@DeleteMapping("/post/{id}")
	public CMRespDto<?> deleteById(@PathVariable Integer id, @LoginUser User principal) {
		Post postEntity = postService.게시글상세보기(id);
		if (principal.getId() == postEntity.getUser().getId()) {
			postService.게시글삭제하기(id);
			return new CMRespDto<>(1, "삭제하기완료", null);
		} else {
			return new CMRespDto<>(-1, "삭제실패 권한없음", null);
		}

	}

	@GetMapping("/init/post")
	public CMRespDto<?> initPost() {
		return new CMRespDto<>(1, "목록보기완료", postService.게시글목록보기());
	}
}
