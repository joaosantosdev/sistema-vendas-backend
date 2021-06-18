package br.com.ourmind.sistemavendas.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ourmind.sistemavendas.domains.Address;
import br.com.ourmind.sistemavendas.domains.Category;
import br.com.ourmind.sistemavendas.domains.City;
import br.com.ourmind.sistemavendas.domains.Client;
import br.com.ourmind.sistemavendas.domains.Item;
import br.com.ourmind.sistemavendas.domains.Order;
import br.com.ourmind.sistemavendas.domains.Payment;
import br.com.ourmind.sistemavendas.domains.PaymentCard;
import br.com.ourmind.sistemavendas.domains.PaymentSlip;
import br.com.ourmind.sistemavendas.domains.Product;
import br.com.ourmind.sistemavendas.domains.State;
import br.com.ourmind.sistemavendas.domains.enums.StatePayment;
import br.com.ourmind.sistemavendas.domains.enums.TypeClient;

@Service
public class DBService {
	
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
	
	@Autowired
	private ItemService itemOrderService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void startDatabaseTest() throws ParseException {
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
		
		
		Client client1 = new Client(null, "Marial Silva", "joaoltj09@gmail.com", "33344455567", TypeClient.LEGAL_PERSON, this.bCryptPasswordEncoder.encode("12345678"));
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
		
		Item io1 = new Item(order1, p1, 0.00, 1, 2000.0);
		Item io2 = new Item(order1, p3, 0.00, 2, 80.0);
		Item io3 = new Item(order2, p2, 100.0, 1, 800.0);
		
		order1.getItems().addAll(Arrays.asList(io1, io2));
		order2.getItems().addAll(Arrays.asList(io3));
		
		p1.getItems().addAll(Arrays.asList(io1));
		p2.getItems().addAll(Arrays.asList(io3));
		p3.getItems().addAll(Arrays.asList(io2));
		
		this.itemOrderService.saveAll(Arrays.asList(io1, io2, io3));
	}
	
}
