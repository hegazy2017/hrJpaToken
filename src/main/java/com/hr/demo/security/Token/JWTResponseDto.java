package com.hr.demo.security.Token;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Builder
@Setter
@Getter
public class JWTResponseDto {
	
	private String accessToken;
	
	private String refreshToken;



}