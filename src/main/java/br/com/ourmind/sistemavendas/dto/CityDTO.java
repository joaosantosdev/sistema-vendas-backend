package br.com.ourmind.sistemavendas.dto;

import java.io.Serializable;

import br.com.ourmind.sistemavendas.domains.City;

public class CityDTO implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;

	public CityDTO(City city) {
		this.id = city.getId();
		this.name = city.getName();
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
	
}
