package com.cos.authjwt.config.filter.jwt;

import org.junit.jupiter.api.Test;

enum UserRole {
	ADMIN, GUEST

	
}

public class EnumTest {
	
	@Test
	public void 이넘테스트() {
		 System.out.println(UserRole.GUEST);
	}
}
