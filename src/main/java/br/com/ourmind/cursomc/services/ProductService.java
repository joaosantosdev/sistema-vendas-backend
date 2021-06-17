package br.com.ourmind.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ourmind.cursomc.domains.Category;
import br.com.ourmind.cursomc.domains.Product;
import br.com.ourmind.cursomc.repositories.CategoryRepository;
import br.com.ourmind.cursomc.repositories.ProductRepository;
import br.com.ourmind.cursomc.services.exeptions.NotFoundResourceException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Page<Product> searchByNameAndCategories(String name, List<Integer> categoryIds, Integer page, Integer perPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, perPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = this.categoryRepository.findAllById(categoryIds);
		return this.productRepository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}
	
	public void saveAll(List<Product> products) {
		this.productRepository.saveAll(products);
	}
	
	public Product getById(Integer id) {
		return this.productRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundResourceException("Produto não encontrado."));
	}

}
