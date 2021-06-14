package br.com.ourmind.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.cursomc.domains.Client;
import br.com.ourmind.cursomc.repositories.ClientRepository;
import br.com.ourmind.cursomc.services.exeptions.NotFoundResourceException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	
	public void saveAll(List<Client> clients) {
		this.clientRepository.saveAll(clients);
	}
	
	public Client getById(Integer id) {
		Optional<Client> client = this.clientRepository.findById(id);
		return client.orElseThrow(() -> new NotFoundResourceException("Cliente não encontrado."));
	}

}
