package com.taco.web;

import com.taco.domain.Taco;
import com.taco.domain.TacoOrder;
import com.taco.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/orders")
@SessionAttributes("addedTacos")
public class OrderController {

	private OrderRepository orderRepository;

	public OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@ModelAttribute(name = "tacoOrder")
	public TacoOrder loadTacoOrder(){
		return new TacoOrder();
	}

	@GetMapping("/current")
	public String getOrderView() {
		return "order";
	}

	@PostMapping("/current")
	public String processOrder(@Valid TacoOrder tacoOrder, Errors error, SessionStatus session, Model model){
		if(error.hasErrors())
			return "order";
		tacoOrder.setTacos((List<Taco>)model.getAttribute("addedTacos"));
		orderRepository.save(tacoOrder);
		session.setComplete();
		return "redirect:/";
	}



	

}
