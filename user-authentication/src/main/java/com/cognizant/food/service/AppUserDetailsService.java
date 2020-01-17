package com.cognizant.food.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.food.model.User;
import com.cognizant.food.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(AppUserDetailsService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	
	public AppUserDetailsService() {
		super();
		LOGGER.info("AppUserDetailService default constructor");
	}
	
	AppUser appUser;
	
	public AppUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository=userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("LoadUserByUserName Start");
		LOGGER.debug("UserRepository:{}", userRepository);
		User user=new User();
		
		user=userRepository.findByUsername(username);
		LOGGER.debug("User:{}", user);
		
		if(user==null)
			throw new UsernameNotFoundException("Username not found");
		else
			appUser=new AppUser(user);
		
		LOGGER.info("LoadUserByUserName End");
		return appUser;
	}
	
	public void signUp(User newUser) throws UserAlreadyExistsException{
		
		LOGGER.info("AppUserDetailsService signUp start");
		
		User user=new User();
		user=userRepository.findByUsername(newUser.getUsername());
		if(user==null) {
			String password=newUser.getPassword();
			newUser.setPassword(passwordEncoder().encode(password));
			userRepository.save(newUser);
		} else
			throw new UserAlreadyExistsException();
		LOGGER.info("AppUserDEtailsService signUp end");
	}
	
	public PasswordEncoder passwordEncoder() {
		LOGGER.info("password encoder");
		return new BCryptPasswordEncoder();
	}

}
