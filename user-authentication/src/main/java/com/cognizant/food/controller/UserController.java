package com.cognizant.food.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.food.model.User;
import com.cognizant.food.service.AppUserDetailsService;
import com.cognizant.food.service.UserAlreadyExistsException;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	
	@PostMapping
	public void signUp(@RequestBody @Valid User user) throws UserAlreadyExistsException{
		appUserDetailsService.signUp(user);
	}

}
