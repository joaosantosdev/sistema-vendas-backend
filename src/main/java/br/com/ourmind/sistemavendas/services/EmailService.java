package br.com.ourmind.sistemavendas.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.ourmind.sistemavendas.domains.Order;

public interface EmailService {
	
	void sendOrderConfirmation(Order order);
	
	void send(SimpleMailMessage message);
	
}
