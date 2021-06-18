package br.com.ourmind.sistemavendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.sistemavendas.domains.Payment;
import br.com.ourmind.sistemavendas.repositories.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	public Payment save(Payment payments) {
		return this.paymentRepository.save(payments);
	}
	
	public void saveAll(List<Payment> payments) {
		this.paymentRepository.saveAll(payments);
	}

}
