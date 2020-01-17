package com.cognizant.food.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.food.model.User;

public class AppUser implements UserDetails {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(User.class);
	
	private User user;
	private Collection<? extends GrantedAuthority> authorities;
	
	public AppUser() {
		super();
		LOGGER.info("AppUser default constructor");
	}
	
	public AppUser(User user) {
		super();
		this.user=user;
		//this.authorities=user.getRoleList().stream().map(role -> new SimpleGrantedAuthority(user.getRoleList().get(0))).collect(Collectors.toList());
		List<GrantedAuthority> authority=new ArrayList<>();
		authority.add(user.getRole());
		this.authorities=authority;
		
		LOGGER.info("AppUser parameterized constructor");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
