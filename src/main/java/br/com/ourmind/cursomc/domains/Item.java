package br.com.ourmind.cursomc.domains;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@JsonIgnore
	private ItemPK id = new ItemPK();
	private Double discount;
	private Integer quantity;
	private Double price;
	
	public Item() {}

	public Item(Order order, Product product, Double discount, Integer quantity, Double price) {
		super();
		this.id.setOrder(order);
		this.id.setProduct(product);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}

	public ItemPK getId() {
		return id;
	}

	public Double getSubTotal() {
		return (this.price - this.discount) * this.quantity;
	}
	
	public void setId(ItemPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@JsonIgnore
	public Order getOrder() {
		return this.id.getOrder();
	}
	
	public void setOrder(Order order) {
		this.id.setOrder(order);
	}
	

	public Product getProduct() {
		return this.id.getProduct();
	}
	
	public void setProduct(Product product) {
		this.id.setProduct(product);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
