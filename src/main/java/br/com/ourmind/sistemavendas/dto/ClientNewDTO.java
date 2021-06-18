package br.com.ourmind.sistemavendas.dto;

import java.io.Serializable;
import java.util.Arrays;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.ourmind.sistemavendas.domains.Address;
import br.com.ourmind.sistemavendas.domains.City;
import br.com.ourmind.sistemavendas.domains.Client;
import br.com.ourmind.sistemavendas.domains.enums.TypeClient;
import br.com.ourmind.sistemavendas.services.validations.ClientNew;

@ClientNew
public class ClientNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@NotEmpty(message="Campo obrigatório.")
	@Length(min=5, max=120, message="Campo deve ter no minimo 5 caracteres e no maximo 120.")
	private String name;
	
	@NotEmpty(message="Campo obrigatório.")
	@Email(message="Campo deve ser email.")
	private String email;
	
	@NotEmpty(message="Campo obrigatório.")
	private String cpfCnpj;
	
	private Integer typeClient;
	
	@NotEmpty(message="Campo obrigatório.")
	private String description;
	
	@NotEmpty(message="Campo obrigatório.")
	private String number;
	
	@NotEmpty(message="Campo obrigatório.")
	private String district;
	
	private String cep;
	
	@NotNull(message="Campo obrigatório.")
	private Integer cityId;
	
	private String phone1;
	
	private String phone2;

	private String phone3;
	
	public ClientNewDTO() {}
	
	public Client toEntity() {
		Client client = new Client(null, this.name,this.email, this.cpfCnpj, TypeClient.toEnum(this.typeClient));
		client.getPhones().add(this.phone1);
		
		if(this.phone2 != null) {
			client.getPhones().add(this.phone2);
		}
		
		if(this.phone3 != null) {
			client.getPhones().add(this.phone3);
		}
		
		City city = new City(this.cityId, null, null);
		Address address = new Address(null, this.description, this.number, this.district, this.cep, client, city);
		client.setAdresses(Arrays.asList(address));
		
		return client;
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Integer getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(Integer typeClient) {
		this.typeClient = typeClient;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}



}
