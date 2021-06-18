package br.com.ourmind.sistemavendas.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ourmind.sistemavendas.domains.Category;
import br.com.ourmind.sistemavendas.domains.Client;
import br.com.ourmind.sistemavendas.dto.ClientDTO;
import br.com.ourmind.sistemavendas.dto.ClientNewDTO;
import br.com.ourmind.sistemavendas.services.ClientService;

@RestController
@RequestMapping(value="clients")
public class ClientResource {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody ClientNewDTO clientNewDTO) {
		Client clientCreated = this.clientService.save(clientNewDTO.toEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}


	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody ClientDTO clientDTO) {
		clientDTO.setId(id);
		this.clientService.update(id, clientDTO.toEntity());
		return ResponseEntity.noContent().build();
	}


	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Client> getById(@PathVariable Integer id) {
		Client client = this.clientService.getById(id);
		return ResponseEntity.ok().body(client);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		this.clientService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> getAll() {
		
		List<ClientDTO> clients = this.clientService.getAll()
				.stream()
				.map(c->new ClientDTO(c))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(clients);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>> getPage(
			@RequestParam(required=false, defaultValue="0") Integer page, 
			@RequestParam(required=false, defaultValue="24")  Integer perPage, 
			@RequestParam(required=false, defaultValue="name")  String orderBy, 
			@RequestParam(required=false, defaultValue="ASC")  String direction) {
		
		Page<ClientDTO> clients = this.clientService.getPage(page, perPage, orderBy, direction)
				.map(c->new ClientDTO(c));
			
		
		return ResponseEntity.ok().body(clients);
	}

}
