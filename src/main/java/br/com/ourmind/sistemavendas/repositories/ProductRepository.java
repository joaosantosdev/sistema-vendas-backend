package br.com.ourmind.sistemavendas.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ourmind.sistemavendas.domains.Category;
import br.com.ourmind.sistemavendas.domains.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("SELECT DISTINCT prod FROM Product prod INNER JOIN prod.categories cat WHERE prod.name LIKE %:name% AND cat IN :categories")
	Page<Product> findDistinctByNameContainingAndCategoriesIn(@Param("name") String name,
			@Param("categories") List<Category> categories, 
			Pageable pageable);

}
