package br.com.ourmind.sistemavendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService{
	
	@Autowired
	private MailSender mailSent;
	
	@Override
	public void send(SimpleMailMessage message) {
		this.mailSent.send(message);
	}

}
