package br.com.ourmind.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ourmind.cursomc.domains.Category;
import br.com.ourmind.cursomc.domains.City;
import br.com.ourmind.cursomc.domains.Product;
import br.com.ourmind.cursomc.domains.State;
import br.com.ourmind.cursomc.services.CategoryService;
import br.com.ourmind.cursomc.services.CityService;
import br.com.ourmind.cursomc.services.ProductService;
import br.com.ourmind.cursomc.services.StateService;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private StateService stateService;
	
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
		
		
		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "São Paulo");
		
		City city1 = new City(null, "Uberlandia", s1);
		City city2 = new City(null, "São Paulo", s2);
		City city3 = new City(null, "Campinas", s2);

		s1.getCities().add(city1);
		s2.getCities().addAll(Arrays.asList(city2, city3));
		
		this.stateService.saveAll(Arrays.asList(s1, s2));
		this.cityService.saveAll(Arrays.asList(city1, city2, city3));

	}

}
