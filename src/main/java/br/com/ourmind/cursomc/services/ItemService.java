package br.com.ourmind.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.cursomc.domains.Item;
import br.com.ourmind.cursomc.repositories.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemOrderRepository;
	
	
	
	public void saveAll(List<Item> items) {
		this.itemOrderRepository.saveAll(items);
	}

}
