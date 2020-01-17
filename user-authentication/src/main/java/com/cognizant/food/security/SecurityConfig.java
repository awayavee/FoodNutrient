package com.cognizant.food.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cognizant.food.service.AppUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	public static Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		LOGGER.info("configure auth start");
		auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
		
		LOGGER.info("configure auth end");
	}
	
	
	@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
				httpSecurity.cors(); 
		httpSecurity.csrf().disable().httpBasic().and().authorizeRequests()
				.antMatchers("/**").permitAll().antMatchers("/authenticate").permitAll()
				.anyRequest().authenticated().and().addFilter(new JwtAuthorizationFilter(authenticationManager()));
				LOGGER.info("Configure HttpSecurity end");
			}  
		 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 LOGGER.info("Start");
		 return new BCryptPasswordEncoder();
	 }
	 

}
