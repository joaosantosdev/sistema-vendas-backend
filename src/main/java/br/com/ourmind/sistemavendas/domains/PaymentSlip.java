package br.com.ourmind.sistemavendas.domains;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.ourmind.sistemavendas.domains.enums.StatePayment;

@Entity
@JsonTypeName("paymentSlip")
public class PaymentSlip extends Payment{
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
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
