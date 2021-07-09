package com.cos.authjwt.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.authjwt.domain.post.Post;
import com.cos.authjwt.domain.post.PostRepository;
import com.cos.authjwt.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	private final PostRepository postRepository;

	@Transactional
	public Post 게시글쓰기(Post post) {
		return postRepository.save(post);
	}

	@Transactional(readOnly = true)
	public List<Post> 게시글목록보기() {
		return postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Transactional(readOnly = true)
	public Post 게시글상세보기(Integer id) {
		return postRepository.findById(id).orElseThrow(
				() -> new CustomApiException("해당 "+id+"는 존재하지 않습니다.")
		);
	}
	
	@Transactional
	public Post 게시글수정하기(Integer id, Post post) {
		Post postEntity = postRepository.findById(id).orElseThrow(
				() -> new CustomApiException("해당 "+id+"는 존재하지 않습니다.")
		);
		postEntity.setTitle(post.getTitle());
		postEntity.setContent(post.getContent());
		
		return postEntity;
	} // 더티 체킹
	
	@Transactional
	public void 게시글삭제하기(Integer id) {
		try {
			postRepository.deleteById(id);
		} catch (Exception e) {
			throw new CustomApiException("해당 "+id+"는 존재하지 않습니다.");
		}
	}

}
