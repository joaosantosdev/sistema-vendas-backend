package br.com.ourmind.sistemavendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.sistemavendas.domains.State;
import br.com.ourmind.sistemavendas.repositories.StateRepository;
import br.com.ourmind.sistemavendas.services.exeptions.NotFoundResourceException;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepository;
	
	public State getById(Integer id) {
		return this.stateRepository.findById(id).orElseThrow(()-> new NotFoundResourceException("Estado não encontrado."));
	}
	
	public List<State> getAll() {
		return this.stateRepository.findAll();
	}
	
	public List<State> getAllOrderName() {
		return this.stateRepository.findAllByOrderByName();
	}
	
	public void saveAll(List<State> states) {
		this.stateRepository.saveAll(states);
	}

}
