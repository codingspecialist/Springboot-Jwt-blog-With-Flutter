package com.cos.authjwt.domain.post;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.authjwt.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // auto_increment
	@Column(nullable = false, length = 100)
	private String title;
	@Column(nullable = false, length = 10000)
	private String content;
	
	@ManyToOne
	private User user;
	
	private LocalDateTime created;
	
	@PrePersist // 디비에 INSERT 되기 직전에 실행
	public void created() {
		this.created = LocalDateTime.now();
	}
}
