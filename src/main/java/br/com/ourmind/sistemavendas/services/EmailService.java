package br.com.ourmind.sistemavendas.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.ourmind.sistemavendas.domains.Client;
import br.com.ourmind.sistemavendas.domains.Order;

public interface EmailService {
	
	void sendOrderConfirmation(Order order);
	
	void send(SimpleMailMessage message);
	
	void sendOrderConfirmationHtml(Order order);
	
	void sendHtml(MimeMessage message);

	void sendNewPassword(Client client, String newPassword);
}
