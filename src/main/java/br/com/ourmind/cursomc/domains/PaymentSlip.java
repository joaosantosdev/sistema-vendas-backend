package br.com.ourmind.cursomc.domains;

import java.util.Date;

import javax.persistence.Entity;

import br.com.ourmind.cursomc.domains.enums.StatePayment;

@Entity
public class PaymentSlip extends Payment{
	private static final long serialVersionUID = 1L;

	private Date dueDate;
	private Date paymentDate;
	
	public PaymentSlip() {}

	public PaymentSlip(Integer id, StatePayment statePayment, Order order, Date dueDate, Date paymentDate) {
		super(id, statePayment, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	
	

}
