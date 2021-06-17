package br.com.ourmind.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ourmind.cursomc.domains.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	@Transactional(readOnly=true)
	Client findByEmail(String email);
}
