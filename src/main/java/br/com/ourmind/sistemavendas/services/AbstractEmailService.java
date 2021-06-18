package br.com.ourmind.sistemavendas.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.ourmind.sistemavendas.domains.Client;
import br.com.ourmind.sistemavendas.domains.Order;

public abstract class AbstractEmailService implements EmailService {
	
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
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
	
	protected String prepareEmailHtml(Order order) {
		Context context = new Context();
		context.setVariable("order", order);
		return this.templateEngine.process("email/orderConfirmation", context);
	}
	
	@Override
	public void sendOrderConfirmationHtml(Order order) {
		try {
			MimeMessage message = this.prepareMimeMessageOrder(order);
			this.sendHtml(message);
		}catch(MessagingException e) {
			this.sendOrderConfirmation(order);
		}
	}

	private MimeMessage prepareMimeMessageOrder(Order order) throws MessagingException {
		MimeMessage message = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(order.getClient().getEmail());
		helper.setFrom(this.sender);
		helper.setSubject("Pedido confirmado! Código: "+ order.getId());
		helper.setSentDate(new Date(System.currentTimeMillis()));
		helper.setText(this.prepareEmailHtml(order), true);
		return message;
	}
	
	@Override
	public void sendNewPassword(Client client, String newPassword) {
		SimpleMailMessage message = prepareSimpleMailMessageNewPassword(client, newPassword);
		this.send(message);
	}

	private SimpleMailMessage prepareSimpleMailMessageNewPassword(Client client, String newPassword) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(client.getEmail());
		message.setFrom(this.sender);
		message.setSubject("Nova senha");
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText("Senha: " + newPassword);
		return message;
	}
	
}
