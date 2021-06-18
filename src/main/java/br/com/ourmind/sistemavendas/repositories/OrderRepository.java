package br.com.ourmind.sistemavendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ourmind.sistemavendas.domains.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
