package br.com.ourmind.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ourmind.cursomc.domains.PaymentCard;
import br.com.ourmind.cursomc.domains.PaymentSlip;

@Configuration
public class JacksonConfig {
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder () {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PaymentCard.class);
				objectMapper.registerSubtypes(PaymentSlip.class);
				super.configure(objectMapper);
			}
		};
		
		return builder;
		
	}

}
