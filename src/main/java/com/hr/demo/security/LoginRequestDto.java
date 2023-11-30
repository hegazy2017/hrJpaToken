package com.hr.demo.security;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {

	@NotEmpty
	private String login;
	@NotEmpty
	private String password;

	public LoginRequestDto(@NotEmpty String login, @NotEmpty String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public LoginRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}