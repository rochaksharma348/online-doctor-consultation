package com.luv2code.springdemo.credentials;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class Credentials {

	@NotNull(message = "cannot be empty")
	@Size(min = 1, message = "cannot be empty")
	private String username;
	
	@NotNull(message = "cannot be empty")
	@Size(min = 1, message = "cannot be empty")
	private String password;
	
	public Credentials() {
		
	}

	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
