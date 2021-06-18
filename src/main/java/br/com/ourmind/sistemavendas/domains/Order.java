package br.com.ourmind.sistemavendas.domains;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="order_table")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date date;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="order")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="address_delivery_id")
	private Address addressDelivery;
	
	@OneToMany(mappedBy="id.order")
	private Set<Item> items = new HashSet<Item>();
	
	public Order() {}

	public Order(Integer id, Date date, Client client, Address addressDelivery) {
		super();
		this.id = id;
		this.date = date;
		this.client = client;
		this.addressDelivery = addressDelivery;
	}
	
	public Double getValueTotal() {

		return this.items
				.stream()
				.map(item->item.getSubTotal())
				.reduce(0.0, (sum, value) -> sum + value);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getAddressDelivery() {
		return addressDelivery;
	}

	public void setAddressDelivery(Address addressDelivery) {
		this.addressDelivery = addressDelivery;
	}
	
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: " + this.id);
		builder.append(", Data: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(this.date));
		builder.append(", Cliente: " + this.client.getName());
		builder.append(", Situacao do pagamento: " + this.payment.getStatePayment().getDescription());
		builder.append("\nDetalhes:\n");
		
		for(Item item : this.items) {
			builder.append(item.toString());
		}
		builder.append("Valor Total: "+nf.format(this.getValueTotal()));
		
		return builder.toString();
	}
}
