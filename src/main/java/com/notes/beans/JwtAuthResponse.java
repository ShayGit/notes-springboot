package com.notes.beans;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class JwtAuthResponse {

		private final String token;
		
		private final String username;


}
