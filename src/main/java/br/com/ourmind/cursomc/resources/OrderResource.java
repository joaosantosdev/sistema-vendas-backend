package br.com.ourmind.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ourmind.cursomc.domains.Order;
import br.com.ourmind.cursomc.services.OrderService;

@RestController
@RequestMapping(value="orders")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;

	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Order> getById(@PathVariable Integer id){
		return ResponseEntity.ok().body(this.orderService.getById(id));
	}
	
}