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
	
	@Autowired
	private StateService stateService;
	

	public List<City> getByState(Integer id) {
		return this.cityRepository.findByState(this.stateService.getById(id));
	}
	
	public List<City> getByStateAndOrderName(Integer id) {
		return this.cityRepository.findByStateOrderByName(this.stateService.getById(id));
	}
	
	
	public void saveAll(List<City> cities) {
		this.cityRepository.saveAll(cities);
	}

}
