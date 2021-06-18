package br.com.ourmind.sistemavendas.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.ourmind.sistemavendas.domains.Order;

public abstract class AbstractEmailService implements EmailService {
	
	
	@Value("${default.sender}")
	private String sender;
	
	public void sendOrderConfirmation(Order order) {
		SimpleMailMessage message = prepareSimpleMailMessageOrder(order);
		this.send(message);
	}

	protected SimpleMailMessage prepareSimpleMailMessageOrder(Order order) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(order.getClient().getEmail());
		message.setFrom(this.sender);
		message.setSubject("Pedido confirmado! Código: "+ order.getId());
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText(order.toString());
		return message;
	}
}
