package com.models.Input;

public class AdminLoginData {

	private String email;

	private String password;

	public AdminLoginData() {
		super();
	}

	public AdminLoginData(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginData [email=" + email + ", password=" + password + "]";
	}

}
