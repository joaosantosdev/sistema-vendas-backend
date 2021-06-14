package br.com.ourmind.cursomc.domains;

import javax.persistence.Entity;

import br.com.ourmind.cursomc.domains.enums.StatePayment;

@Entity
public class PaymentCard extends Payment{
	private static final long serialVersionUID = 1L;

	private Integer numberParcel;
	
	public PaymentCard() {}

	public PaymentCard(Integer id, StatePayment statePayment, Order order, Integer numberParcel) {
		super(id, statePayment, order);
		this.numberParcel = numberParcel;
	}

	public Integer getNumberParcel() {
		return numberParcel;
	}

	public void setNumberParcel(Integer numberParcel) {
		this.numberParcel = numberParcel;
	}
	
	
	
	
}
