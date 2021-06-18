package br.com.ourmind.sistemavendas.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService{
	
	@Autowired
	private MailSender mailSent;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void send(SimpleMailMessage message) {
		this.mailSent.send(message);
	}

	@Override
	public void sendHtml(MimeMessage message) {
		this.javaMailSender.send(message);
	}

}
