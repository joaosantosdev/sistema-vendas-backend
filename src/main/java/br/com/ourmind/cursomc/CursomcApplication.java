package br.com.ourmind.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ourmind.cursomc.domains.Category;
import br.com.ourmind.cursomc.domains.Product;
import br.com.ourmind.cursomc.services.CategoryService;
import br.com.ourmind.cursomc.services.ProductService;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;


	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category c1 = new Category(null, "Informática");
		Category c2 = new Category(null, "Escritorio");
		
		Product p1 = new Product(null, "Computador", 3000.0);
		Product p2 = new Product(null, "Impressora", 500.0);
		Product p3 = new Product(null, "Mouse", 80.0);

		c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		c2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().add(c1);
		p2.getCategories().addAll(Arrays.asList(c1, c2));
		p3.getCategories().add(c1);

		this.categoryService.saveAll(Arrays.asList(c1,	c2));

		this.productService.saveAll(Arrays.asList(p1, p2, p3));
		
	}

}
