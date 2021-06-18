package br.com.ourmind.sistemavendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ourmind.sistemavendas.domains.Item;
import br.com.ourmind.sistemavendas.repositories.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemOrderRepository;
	
	
	@Transactional
	public Item save(Item item) {
		return this.itemOrderRepository.save(item);
	}

	public void saveAll(List<Item> items) {
		this.itemOrderRepository.saveAll(items);
	}

}
