package br.com.ourmind.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ourmind.cursomc.domains.Order;
import br.com.ourmind.cursomc.repositories.OrderRepository;
import br.com.ourmind.cursomc.services.exeptions.NotFoundResourceException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Order getById(Integer id) {
		return this.orderRepository.findById(id).orElseThrow(()-> new NotFoundResourceException("Pedido não encontrado"));
	}
	
	public void saveAll(List<Order> orders) {
		this.orderRepository.saveAll(orders);
	}

}
