package br.com.ourmind.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ourmind.cursomc.domains.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
