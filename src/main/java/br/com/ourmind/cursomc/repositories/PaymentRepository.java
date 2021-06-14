package br.com.ourmind.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ourmind.cursomc.domains.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}