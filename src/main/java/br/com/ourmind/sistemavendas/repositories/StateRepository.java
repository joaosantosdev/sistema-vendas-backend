package br.com.ourmind.sistemavendas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ourmind.sistemavendas.domains.State;


@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
	
	
	List<State> findAllByOrderByName();
	
}
