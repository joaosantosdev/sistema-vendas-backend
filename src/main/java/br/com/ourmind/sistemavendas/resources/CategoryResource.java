package br.com.ourmind.sistemavendas.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ourmind.sistemavendas.domains.Category;
import br.com.ourmind.sistemavendas.dto.CategoryDTO;
import br.com.ourmind.sistemavendas.services.CategoryService;


@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@Autowired 
	private CategoryService categoryService;
	

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody CategoryDTO categoryDTO) {
		Category categoryCreated= this.categoryService.save(categoryDTO.toEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoryCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody CategoryDTO categoryDTO) {
		categoryDTO.setId(id);
		this.categoryService.update(id, categoryDTO.toEntity());
		return ResponseEntity.noContent().build();
	}


	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Category> getById(@PathVariable Integer id) {
		Category category = this.categoryService.getById(id);
		return ResponseEntity.ok().body(category);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		this.categoryService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<CategoryDTO>> getAll() {
		
		List<CategoryDTO> categories = this.categoryService.getAll()
				.stream()
				.map(c->new CategoryDTO(c))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(categories);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> getPage(
			@RequestParam(required=false, defaultValue="0") Integer page, 
			@RequestParam(required=false, defaultValue="24")  Integer perPage, 
			@RequestParam(required=false, defaultValue="name")  String orderBy, 
			@RequestParam(required=false, defaultValue="ASC")  String direction) {
		
		Page<CategoryDTO> categories = this.categoryService.getPage(page, perPage, orderBy, direction)
				.map(c->new CategoryDTO(c));
			
		
		return ResponseEntity.ok().body(categories);
	}
}
