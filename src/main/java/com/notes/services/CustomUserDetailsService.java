package com.notes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.notes.beans.CustomUserDetails;
import com.notes.beans.User;
import com.notes.exceptions.UserAlreadyExistException;
import com.notes.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;
    
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
   
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user ==null) {
        	throw new UsernameNotFoundException(username);
        }
        //UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities("USER").build();

		return new CustomUserDetails(user);
	}
	
    public void register(User user) throws Exception {
    	
    	if(userRepository.findByUsername(user.getUsername()) !=null) {
    		throw new UserAlreadyExistException("This username already exists.");
    	}
    	
    	final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    	user.setPassword(encryptedPassword);

    	userRepository.save(user);
    }
	

}
