package br.com.ourmind.sistemavendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ourmind.sistemavendas.domains.Client;
import br.com.ourmind.sistemavendas.repositories.ClientRepository;
import br.com.ourmind.sistemavendas.services.exeptions.DataIntegrityException;
import br.com.ourmind.sistemavendas.services.exeptions.NotFoundResourceException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public void saveAll(List<Client> clients) {
		this.clientRepository.saveAll(clients);
	}

	
	@Transactional
	public Client save(Client client) {
		client.setId(null);
		this.addressService.saveAll(client.getAdresses());
		client.setPassword(this.bCryptPasswordEncoder.encode(client.getPassword()));
		return this.clientRepository.save(client);
	}
	
	public Client update(Integer id, Client clientData) {
		Client client = this.getById(id);
		this.updateData(client, clientData);
		return this.clientRepository.save(client);
	}
	
	public Client getById(Integer id) {
		Optional<Client> client = this.clientRepository.findById(id);
		return client.orElseThrow(()-> new NotFoundResourceException("Categoria não encontrado"));
	}
	
	public void deleteById(Integer id) {
		try {
			this.getById(id);
			this.clientRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir pois possui vinculos.");
		}
	}
	
	public List<Client> getAll() {
		return this.clientRepository.findAll();
	}
	
	public Page<Client> getPage(Integer page, Integer perPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, perPage, Direction.valueOf(direction), orderBy);
		return this.clientRepository.findAll(pageRequest);
	}
	
	public void updateData(Client client,Client clientData){
		client.setName(clientData.getName());
		client.setEmail(clientData.getEmail());
	}
	
	public Client getByEmail(String email) {
		return this.clientRepository.findByEmail(email);
	}
}
