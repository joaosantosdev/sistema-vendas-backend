package br.com.ourmind.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.cursomc.domains.State;
import br.com.ourmind.cursomc.repositories.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepository;
	
	
	
	public void saveAll(List<State> states) {
		this.stateRepository.saveAll(states);
	}

}
