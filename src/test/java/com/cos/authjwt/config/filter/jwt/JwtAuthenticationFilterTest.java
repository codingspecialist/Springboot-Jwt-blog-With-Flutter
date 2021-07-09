package com.cos.authjwt.config.filter.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.cos.authjwt.web.dto.user.LoginReqDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilterTest {

	@Test
	public void 제이슨to오브젝트() {
		String data = "{\"username\":\"ssar\", \"password\":\"1234\"}";
		ObjectMapper om = new ObjectMapper();
		try {
			LoginReqDto loginReqDto =  om.readValue(data, LoginReqDto.class);
			System.out.println(loginReqDto);
			
			assertNotNull(loginReqDto.getUsername());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
