package br.com.ourmind.sistemavendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.sistemavendas.domains.City;
import br.com.ourmind.sistemavendas.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	
	
	public void saveAll(List<City> cities) {
		this.cityRepository.saveAll(cities);
	}

}
