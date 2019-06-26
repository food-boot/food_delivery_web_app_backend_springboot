package com.food.delivery.app.ws.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@CrossOrigin
	@GetMapping(path="/{id}", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OrderDetailsResponse getOrder(@PathVariable String id) {
		
		OrderDetailsResponse returnValue = new OrderDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		OrderDto orderDto = orderService.getOrderById(id);
		returnValue = modelMapper.map(orderDto, OrderDetailsResponse.class);
		
		return returnValue;
	}
	
	@CrossOrigin
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		
		OrderDetailsResponse returnValue = new OrderDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		OrderDto orderDto = new OrderDto();
		BeanUtils.copyProperties(order, orderDto);
		
		OrderDto createdOrder = orderService.createOrder(orderDto);
		returnValue = modelMapper.map(createdOrder, OrderDetailsResponse.class);
		
		return returnValue;
	}
		
	@CrossOrigin
	@PutMapping(path="/{id}", 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) {
		
		OrderDetailsResponse returnValue = new OrderDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		OrderDto orderDto = new OrderDto();
		orderDto = modelMapper.map(order, OrderDto.class);
		
		OrderDto updatedOrder = orderService.updateOrderDetails(id, orderDto);
		
		returnValue = modelMapper.map(updatedOrder, OrderDetailsResponse.class);
		return returnValue;
	}
	
	@CrossOrigin
	@DeleteMapping
	public String deleteOrder() {
		return "delete order method is called";
	}
}
