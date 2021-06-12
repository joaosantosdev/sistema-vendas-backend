package br.com.ourmind.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.cursomc.domains.Product;
import br.com.ourmind.cursomc.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	
	public void saveAll(List<Product> products) {
		this.productRepository.saveAll(products);
	}

}
