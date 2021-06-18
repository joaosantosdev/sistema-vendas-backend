package br.com.ourmind.sistemavendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.sistemavendas.domains.Address;
import br.com.ourmind.sistemavendas.repositories.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	
	public void saveAll(List<Address> adresses) {
		this.addressRepository.saveAll(adresses);
	}

}
