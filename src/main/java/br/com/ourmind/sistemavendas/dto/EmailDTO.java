package br.com.ourmind.sistemavendas.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable{
	public static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Campo obrigatório.")
	@Email(message="Email inválido")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
