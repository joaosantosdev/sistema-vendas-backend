package br.com.ourmind.sistemavendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ourmind.sistemavendas.domains.Item;
import br.com.ourmind.sistemavendas.domains.ItemPK;

@Repository
public interface ItemRepository extends JpaRepository<Item, ItemPK> {

}
