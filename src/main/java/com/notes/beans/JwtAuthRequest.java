package com.notes.beans;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthRequest {

	
	@NotNull
	@NotBlank(message = "Username is mandatory")
	private String username;
	@NotNull
	@NotBlank(message = "Password is mandatory")
	private String password;
}
