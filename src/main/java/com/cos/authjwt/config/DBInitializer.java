package com.cos.authjwt.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cos.authjwt.domain.post.Post;
import com.cos.authjwt.domain.post.PostRepository;
import com.cos.authjwt.domain.user.User;
import com.cos.authjwt.domain.user.UserRepository;

@Configuration
public class DBInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(DBInitializer.class);

	    @Bean
	    public CommandLineRunner demo(UserRepository userRepository, PostRepository postRepository) {

	        return (args) -> {
	            List<User> users = new ArrayList<>();
	            users.add(User.builder().username("ssar").password("1234").email("ssar@nate.com").build());
	            users.add(User.builder().username("cos").password("1234").email("cos@nate.com").build());
	        	userRepository.saveAll(users);

	            List<Post> posts = new ArrayList<>();
	            posts.add(Post.builder().title("제목1").content("내용1").user(User.builder().id(1).build()).build());
	            posts.add(Post.builder().title("제목2").content("내용2").user(User.builder().id(1).build()).build());
	            posts.add(Post.builder().title("제목3").content("내용3").user(User.builder().id(1).build()).build());
	            posts.add(Post.builder().title("제목4").content("내용4").user(User.builder().id(2).build()).build());
	            posts.add(Post.builder().title("제목5").content("내용5").user(User.builder().id(2).build()).build());
	        	postRepository.saveAll(posts);
	        };
	    }
}
