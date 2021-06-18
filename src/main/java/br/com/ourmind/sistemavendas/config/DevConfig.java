package br.com.ourmind.sistemavendas.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ourmind.sistemavendas.services.DBService;
import br.com.ourmind.sistemavendas.services.EmailService;
import br.com.ourmind.sistemavendas.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	public String strategy;
	
	@Autowired
	private DBService dbService;
	
	@Bean 
	public boolean startDatabaseTest() throws ParseException {
		
		if(this.strategy.equals("create"))
			this.dbService.startDatabaseTest();
		
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
