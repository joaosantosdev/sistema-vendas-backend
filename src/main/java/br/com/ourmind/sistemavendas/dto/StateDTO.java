package br.com.ourmind.sistemavendas.dto;

import java.io.Serializable;

import br.com.ourmind.sistemavendas.domains.State;

public class StateDTO implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;

	public StateDTO(State state) {
		this.id = state.getId();
		this.name = state.getName();
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
