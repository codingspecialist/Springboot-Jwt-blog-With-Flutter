package com.cos.authjwt.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // auto_increment
	
	@Column(length = 20,  unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String email;
	
	private LocalDateTime created;
	
	@PrePersist // 디비에 INSERT 되기 직전에 실행
	public void created() {
		this.created = LocalDateTime.now();
	}
}
