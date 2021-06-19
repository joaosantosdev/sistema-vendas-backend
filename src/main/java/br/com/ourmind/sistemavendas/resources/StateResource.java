package br.com.ourmind.sistemavendas.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ourmind.sistemavendas.dto.CityDTO;
import br.com.ourmind.sistemavendas.dto.StateDTO;
import br.com.ourmind.sistemavendas.services.CityService;
import br.com.ourmind.sistemavendas.services.StateService;

@RestController
@RequestMapping(value="/states")
public class StateResource {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> getStates(){
		List<StateDTO> states = this.stateService.getAllOrderName()
				.stream()
				.map(state->new StateDTO(state))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(states);
	}
	
	@RequestMapping(value="/{stateId}/cities", method=RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> getCitiesByState(@PathVariable Integer stateId){
		List<CityDTO> cities = this.cityService.getByStateAndOrderName(stateId)
				.stream()
				.map(city->new CityDTO(city))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(cities);
	}


}
