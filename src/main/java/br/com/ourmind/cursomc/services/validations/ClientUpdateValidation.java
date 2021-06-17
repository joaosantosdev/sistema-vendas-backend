package br.com.ourmind.cursomc.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.ourmind.cursomc.domains.Client;
import br.com.ourmind.cursomc.dto.ClientDTO;
import br.com.ourmind.cursomc.resources.exceptions.FieldError;
import br.com.ourmind.cursomc.services.ClientService;

public class ClientUpdateValidation implements ConstraintValidator<ClientUpdate, ClientDTO>{
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private ClientService clientService;
	
	@Override 
	public void initialize(ClientUpdate ann) {
		
	}

	@Override
	public boolean isValid(ClientDTO clientDTO, ConstraintValidatorContext context) {
		
		Map<String, String> map = (Map) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer id = Integer.valueOf(map.get("id"));
		
		List<FieldError> errors = new ArrayList<>();
		
		Client client = this.clientService.getByEmail(clientDTO.getEmail());

		if(client != null && !client.getId().equals(id)) {
			errors.add(new FieldError("email","E-mail já existente"));
		}
		
		for(FieldError error: errors) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMessage())
			.addPropertyNode(error.getField())
			.addConstraintViolation();
		}
		
		return errors.isEmpty();
	}

}
