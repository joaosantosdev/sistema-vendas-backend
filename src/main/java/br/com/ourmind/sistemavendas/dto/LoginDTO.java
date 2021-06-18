package br.com.ourmind.sistemavendas.dto;

import javax.validation.constraints.Email;

public class LoginDTO {
	
	@Email(message="Email inválido")
	private String email;
	
	private String password;
	
	public LoginDTO() {}

	public LoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String senha) {
		this.password = senha;
	}

	
	
	
}
