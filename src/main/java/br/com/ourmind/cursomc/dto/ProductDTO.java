package br.com.ourmind.cursomc.dto;

import java.io.Serializable;

import br.com.ourmind.cursomc.domains.Product;

public class ProductDTO  implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private Double price;
	
	public ProductDTO() {}
	
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
