package br.com.ourmind.sistemavendas.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.ourmind.sistemavendas.domains.Client;
import br.com.ourmind.sistemavendas.services.validations.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Campo obrigatório.")
	@Length(min=5, max=120, message="Campo deve ter no minimo 5 caracteres e no maximo 120.")
	private String name;
	
	
	@NotEmpty(message="Campo obrigatório.")
	@Email(message="Campo deve ser email.")
	private String email;
	
	public ClientDTO() {}
	
	public ClientDTO(Client client) {
		this.id = client.getId();
		this.name = client.getName();
		this.email = client.getEmail();
	}
	
	public Client toEntity() {
		Client client = new Client();
		client.setId(this.id);
		client.setName(this.name);
		client.setEmail(this.email);
		return client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
