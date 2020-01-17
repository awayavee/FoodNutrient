package com.cognizant.food.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="us_id")
	private int userId;
	
	@Column(name="us_name")
	@NotNull(message="User Name is required")
	private String username;
	
	@Column(name="us_first_name")
	private String firstName;
	
	@Column(name="us_last_name")
	private String lastName;
	
	@Column(name="us_role")
	private String role;
	
	@Column(name="us_password")
	@Size(min=2, max=100, message="Min len 2 and Max len 100")
	private String password;
	
	public User() {
		super();
	}	
	
	public User(int userId, @NotNull(message = "User Name is required") String username, String firstName,
			String lastName,
			@Size(min = 2, max = 100, message = "Min len 2 and Max len 100") String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = "ROLE_USER";
//		this.roleList = new ArrayList<>();
//		this.roleList.add("ROLE_USER");
		this.password = password;
	}

	public GrantedAuthority getRole() {
		return new SimpleGrantedAuthority(role);
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
