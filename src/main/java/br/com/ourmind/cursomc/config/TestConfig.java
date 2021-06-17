package br.com.ourmind.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ourmind.cursomc.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean 
	public boolean startDatabaseTest() throws ParseException {
		this.dbService.startDatabaseTest();
		return true;
	}

}
