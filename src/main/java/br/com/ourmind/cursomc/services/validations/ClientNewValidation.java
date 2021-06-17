package br.com.ourmind.cursomc.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ourmind.cursomc.domains.Client;
import br.com.ourmind.cursomc.domains.enums.TypeClient;
import br.com.ourmind.cursomc.dto.ClientNewDTO;
import br.com.ourmind.cursomc.resources.exceptions.FieldError;
import br.com.ourmind.cursomc.services.ClientService;
import br.com.ourmind.cursomc.utils.CpfCnpjUtil;

public class ClientNewValidation implements ConstraintValidator<ClientNew, ClientNewDTO>{
	
	@Autowired
	private ClientService clientService;
	
	@Override 
	public void initialize(ClientNew ann) {
		
	}

	@Override
	public boolean isValid(ClientNewDTO clientDTO, ConstraintValidatorContext context) {
		
		List<FieldError> errors = new ArrayList<>();
		
		Client client = this.clientService.getByEmail(clientDTO.getEmail());
		
		if( !CpfCnpjUtil.isValid(clientDTO.getCpfCnpj())) {
			boolean isCpf = clientDTO.getTypeClient() == TypeClient.NATURAL_PERSON.getId();
			errors.add(new FieldError("cpfCnpj", (isCpf ? "CPF": "CNPJ") + " inválido"));
		}
		
		if(client != null) {
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
