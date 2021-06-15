package br.com.ourmind.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ourmind.cursomc.domains.Category;
import br.com.ourmind.cursomc.repositories.CategoryRepository;
import br.com.ourmind.cursomc.services.exeptions.DataIntegrityException;
import br.com.ourmind.cursomc.services.exeptions.NotFoundResourceException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category save(Category category) {
		category.setId(null);
		return this.categoryRepository.save(category);
	}
	
	public Category update(Integer id, Category category) {
		this.getById(id);
		return this.categoryRepository.save(category);
	}
	
	public Category getById(Integer id) {
		Optional<Category> category = this.categoryRepository.findById(id);
		return category.orElseThrow(()-> new NotFoundResourceException("Categoria não encontrado"));
	}
	
	public void saveAll(List<Category> categories) {
		this.categoryRepository.saveAll(categories);
	}
	
	public void deleteById(Integer id) {
		try {
			this.getById(id);
			this.categoryRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir pois possui vinculos.");
		}
	}
	
	public List<Category> getAll() {
		return this.categoryRepository.findAll();
	}
	
	public Page<Category> getPage(Integer page, Integer perPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, perPage, Direction.valueOf(direction), orderBy);
		return this.categoryRepository.findAll(pageRequest);
	}
}
