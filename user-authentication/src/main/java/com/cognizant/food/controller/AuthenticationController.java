package com.cognizant.food.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
public class AuthenticationController {

	public static Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader(value="Authorization", required=false) String authHeader) {
		LOGGER.debug("Start");
		String user = getUser(authHeader);

		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
		Map<String, String> auth = new HashMap<String, String>();
		auth.put("token", generateJwt(user, role));

		LOGGER.debug("authenticate(): ROLE: " + role);

		auth.put("role", role);
		LOGGER.debug("End");
		return auth;
	}

	private String getUser(String authHeader) {
		byte[] auth = Base64.getDecoder().decode(authHeader.split(" ")[1]);
		String authStr = new String(auth);
		return authStr.split(":")[0];
	}

	private String generateJwt(String user, String role) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user).claim("role", role).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + 1200000))
				.signWith(SignatureAlgorithm.HS256, "secretkey");
		String token = builder.compact();

		return token;

	}

}