package br.com.ourmind.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.cursomc.domains.Payment;
import br.com.ourmind.cursomc.repositories.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	
	public void saveAll(List<Payment> payments) {
		this.paymentRepository.saveAll(payments);
	}

}
