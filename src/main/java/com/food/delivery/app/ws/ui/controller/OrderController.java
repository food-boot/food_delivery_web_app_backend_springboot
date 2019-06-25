package com.food.delivery.app.ws.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.delivery.app.ws.model.request.OrderDetailsRequestModel;
import com.food.delivery.app.ws.model.response.OrderDetailsResponse;
import com.food.delivery.app.ws.service.OrderService;
import com.food.delivery.app.ws.shared.dto.OrderDto;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;

	@GetMapping
	public String getOrder() {
		return "get order method is called";
	}
	
	@PostMapping
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		
		OrderDetailsResponse returnValue = new OrderDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		OrderDto orderDto = new OrderDto();
		BeanUtils.copyProperties(order, orderDto);
		
		OrderDto createdOrder = orderService.createOrder(orderDto);
		returnValue = modelMapper.map(createdOrder, OrderDetailsResponse.class);
		
		return returnValue;
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
