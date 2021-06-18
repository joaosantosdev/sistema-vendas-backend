package br.com.ourmind.sistemavendas.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ourmind.sistemavendas.domains.Order;
import br.com.ourmind.sistemavendas.services.OrderService;

@RestController
@RequestMapping(value="orders")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Order order) {
		Order orderCreated= this.orderService.save(order);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(orderCreated.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Order> getById(@PathVariable Integer id){
		return ResponseEntity.ok().body(this.orderService.getById(id));
	}
	
}
