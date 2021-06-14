package br.com.ourmind.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.cursomc.domains.Address;
import br.com.ourmind.cursomc.repositories.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	
	public void saveAll(List<Address> adresses) {
		this.addressRepository.saveAll(adresses);
	}

}
