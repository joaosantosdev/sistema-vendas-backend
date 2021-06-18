package br.com.ourmind.sistemavendas.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ourmind.sistemavendas.domains.Item;
import br.com.ourmind.sistemavendas.domains.Order;
import br.com.ourmind.sistemavendas.domains.PaymentSlip;
import br.com.ourmind.sistemavendas.domains.Product;
import br.com.ourmind.sistemavendas.domains.enums.StatePayment;
import br.com.ourmind.sistemavendas.repositories.OrderRepository;
import br.com.ourmind.sistemavendas.services.exeptions.NotFoundResourceException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	public Order getById(Integer id) {
		return this.orderRepository.findById(id).orElseThrow(()-> new NotFoundResourceException("Pedido não encontrado"));
	}
	
	public void saveAll(List<Order> orders) {
		this.orderRepository.saveAll(orders);
	}
	
	@Transactional
	public Order save(Order order) {
		order.setId(null);
		Date now = new Date();
		order.setDate(now);
		order.getPayment().setStatePayment(StatePayment.PENDENT);
		order.getPayment().setOrder(order);
		order.setClient(this.clientService.getById(order.getClient().getId()));
		
		if(order.getPayment() instanceof PaymentSlip) {
			PaymentSlip payment = (PaymentSlip) order.getPayment();
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			cal.add(Calendar.DAY_OF_MONTH, 7);
			payment.setDueDate(cal.getTime());
		}

		order = this.orderRepository.save(order);
		this.paymentService.save(order.getPayment());
		
		for(Item item : order.getItems()) {
			item.setDiscount(0.0);
			Product product = this.productService.getById(item.getProduct().getId());
			item.setPrice(product.getPrice());
			item.setOrder(order);
			item.setProduct(product);
			this.itemService.save(item);
		}
		
		this.emailService.sendOrderConfirmationHtml(order);
		return order;
	}

}
