package br.com.ourmind.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ourmind.cursomc.domains.Address;
import br.com.ourmind.cursomc.domains.Category;
import br.com.ourmind.cursomc.domains.City;
import br.com.ourmind.cursomc.domains.Client;
import br.com.ourmind.cursomc.domains.Order;
import br.com.ourmind.cursomc.domains.Payment;
import br.com.ourmind.cursomc.domains.PaymentCard;
import br.com.ourmind.cursomc.domains.PaymentSlip;
import br.com.ourmind.cursomc.domains.Product;
import br.com.ourmind.cursomc.domains.State;
import br.com.ourmind.cursomc.domains.enums.StatePayment;
import br.com.ourmind.cursomc.domains.enums.TypeClient;
import br.com.ourmind.cursomc.services.AddressService;
import br.com.ourmind.cursomc.services.CategoryService;
import br.com.ourmind.cursomc.services.CityService;
import br.com.ourmind.cursomc.services.ClientService;
import br.com.ourmind.cursomc.services.OrderService;
import br.com.ourmind.cursomc.services.PaymentService;
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
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PaymentService paymentService;
	
	
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
		
		
		Client client1 = new Client(null, "Marial Silva", "maria@gmaill.com", "33344455567", TypeClient.LEGAL_PERSON);
		client1.getPhones().addAll(Arrays.asList("8599989339", "8599189339"));
		
		Address adr1 = new Address(null, "Rua c", "03", "Alto alegre", "60821470", client1, city1);
		Address adr2 = new Address(null, "Rua b", "03", "Alto alegre", "60821470", client1, city2);
		
		client1.getAdresses().addAll(Arrays.asList(adr1, adr2));
		
		this.clientService.saveAll(Arrays.asList(client1));
		this.addressService.saveAll(Arrays.asList(adr1, adr2));
		
		SimpleDateFormat d1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Order order1 = new Order(null, d1.parse("25/09/2021 14:00"), client1, adr1);
		Order order2 = new Order(null, d1.parse("20/09/2021 13:00"), client1, adr2);
		
		Payment payment1 = new PaymentCard(null, StatePayment.FINISHED, order1, 6);
		order1.setPayment(payment1);
		
		Payment payment2 = new PaymentSlip(null, StatePayment.PENDENT, order2, d1.parse("20/10/2021 00:00"), null);
		order2.setPayment(payment2);
		
		client1.getOrders().addAll(Arrays.asList(order1, order2));
		
		this.orderService.saveAll(Arrays.asList(order1, order2));
		this.paymentService.saveAll(Arrays.asList(payment1, payment2));

	}

}
