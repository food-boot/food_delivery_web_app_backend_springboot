package com.food.delivery.app.ws.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@GetMapping
	public String getOrder() {
		return "get order method is called";
	}
	
	@PostMapping
	public String createOrder() {
		return "create order method is called";
	}
	
	@PutMapping
	public String updateOrder() {
		return "update order method is called";
	}
	
	@DeleteMapping
	public String deleteOrder() {
		return "delete order method is called";
	}
}
