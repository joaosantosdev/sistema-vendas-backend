package br.com.ourmind.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.cursomc.domains.Category;
import br.com.ourmind.cursomc.repositories.CategoryRepository;
import br.com.ourmind.cursomc.services.exeptions.NotFoundResourceException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category getById(Integer id) {
		Optional<Category> category = this.categoryRepository.findById(id);
		return category.orElseThrow(()-> new NotFoundResourceException("Produto não encontrado"));
	}
	
	
	public void saveAll(List<Category> categories) {
		this.categoryRepository.saveAll(categories);
	}

}
