package br.com.ourmind.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ourmind.cursomc.domains.Item;
import br.com.ourmind.cursomc.domains.ItemPK;

@Repository
public interface ItemRepository extends JpaRepository<Item, ItemPK> {

}
