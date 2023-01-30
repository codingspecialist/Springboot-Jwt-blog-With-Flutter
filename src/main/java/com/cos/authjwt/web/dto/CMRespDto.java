package com.cos.authjwt.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CMRespDto<T> {
	private final int code;
	private String token;
	private final String msg;
	private final T data;
}
