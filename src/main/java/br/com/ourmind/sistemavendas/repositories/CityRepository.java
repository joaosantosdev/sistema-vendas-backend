package br.com.ourmind.sistemavendas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ourmind.sistemavendas.domains.City;
import br.com.ourmind.sistemavendas.domains.State;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	
	List<City> findByState(State state);
	
	List<City> findByStateOrderByName(State state);

}
