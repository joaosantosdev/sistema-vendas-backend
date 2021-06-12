package br.com.ourmind.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ourmind.cursomc.domains.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
