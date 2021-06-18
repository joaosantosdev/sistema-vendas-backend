package br.com.ourmind.sistemavendas.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class LoginDTO {
	
	@NotEmpty(message="Campo obrigatório.")
	@Email(message="Email inválido")
	private String email;
	
	@Length(min=8, max=120, message="Campo deve ter no minimo 5 caracteres e no maximo 120.")
	@NotEmpty(message="Campo obrigatório.")
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
