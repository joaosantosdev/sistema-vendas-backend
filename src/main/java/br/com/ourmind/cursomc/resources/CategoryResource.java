package br.com.ourmind.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ourmind.cursomc.domains.Category;
import br.com.ourmind.cursomc.services.CategoryService;


@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@Autowired 
	private CategoryService categoryService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Category> getById(@PathVariable Integer id) {
		Category category = this.categoryService.getById(id);
		return ResponseEntity.ok().body(category);
	}

}
