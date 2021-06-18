package br.com.ourmind.sistemavendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.sistemavendas.domains.State;
import br.com.ourmind.sistemavendas.repositories.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepository;
	
	
	
	public void saveAll(List<State> states) {
		this.stateRepository.saveAll(states);
	}

}
