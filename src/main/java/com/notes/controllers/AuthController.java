package com.notes.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.beans.JwtAuthRequest;
import com.notes.beans.JwtAuthResponse;
import com.notes.beans.User;
import com.notes.services.CustomUserDetailsService;
import com.notes.services.JwtTokenUtil;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@GetMapping("hello")
	public String Hello() {
		return "Hello world";
	}
	
	@PostMapping("register")
	public ResponseEntity<?> registerNewUser(@Valid @RequestBody JwtAuthRequest authRegisterRequest) throws Exception{
		userDetailsService.register(new User(authRegisterRequest.getUsername(),authRegisterRequest.getPassword()));

		return ResponseEntity.ok("Account created successfully.");
	}
	
	@PostMapping("login")
	public ResponseEntity<?> createAuthToken(@Valid @RequestBody JwtAuthRequest authRequest) throws Exception{
		authenticate(authRequest.getUsername(),authRequest.getPassword());
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtAuthResponse(token, userDetails.getUsername()));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}
