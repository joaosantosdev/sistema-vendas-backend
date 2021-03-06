package br.com.ourmind.sistemavendas.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void send(SimpleMailMessage message) {
		MockEmailService.LOG.info("Simulando envio de email");
		MockEmailService.LOG.info(message.toString());
		MockEmailService.LOG.info("Email enviado");

	}

	@Override
	public void sendHtml(MimeMessage message) {
		MockEmailService.LOG.info("Simulando envio de email html");
		MockEmailService.LOG.info(message.toString());
		MockEmailService.LOG.info("Email enviado");
	}

}
