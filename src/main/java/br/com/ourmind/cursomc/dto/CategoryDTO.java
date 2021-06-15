package br.com.ourmind.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.ourmind.cursomc.domains.Category;

public class CategoryDTO implements Serializable{
	public static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	@NotEmpty(message="Campo obrigatório")
	@Length(min=5, max=80, message="Minimo de 5 caracteres e maximo de 80.")
	private String name;
	
	public CategoryDTO() {}
	
	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
	}
	
	public Category toEntity() {
		return new Category(this.id, this.name);
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
