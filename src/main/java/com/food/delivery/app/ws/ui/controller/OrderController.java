package com.food.delivery.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
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
import com.food.delivery.app.ws.model.response.OperationStatusModel;
import com.food.delivery.app.ws.model.response.OrderDetailsResponse;
import com.food.delivery.app.ws.model.response.RequestOperationName;
import com.food.delivery.app.ws.model.response.RequestOperationStatus;
import com.food.delivery.app.ws.service.OrderService;
import com.food.delivery.app.ws.shared.dto.OrderDto;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;

	/**
	 * Initialize Logger object
	 */
	org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@CrossOrigin
	@ApiOperation(value = "The Get Order Details Web Service Endpoint", notes = "This web service endpoint returns the Order detials with json format or xml format")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@GetMapping(path="/{id}", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OrderDetailsResponse getOrder(@PathVariable String id) {
		
		logger.info("OrderController -> getOrder(id) method has been called");
		OrderDetailsResponse returnValue = new OrderDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		OrderDto orderDto = orderService.getOrderById(id);
		returnValue = modelMapper.map(orderDto, OrderDetailsResponse.class);
		
		logger.info("Return the order to the requester");
		return returnValue;
	}
	
	@CrossOrigin
	@ApiOperation(value = "The Create Order Web Service Endpoint", notes = "This web service endpoint returns the Order detials with json format or xml format when the order is created")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		
		logger.info("OrderController -> createOrder(order) method has been called");
		OrderDetailsResponse returnValue = new OrderDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		OrderDto orderDto = new OrderDto();
		BeanUtils.copyProperties(order, orderDto);
		
		OrderDto createdOrder = orderService.createOrder(orderDto);
		returnValue = modelMapper.map(createdOrder, OrderDetailsResponse.class);
		
		logger.info("Return the order to the requester");
		return returnValue;
	}
		
	@CrossOrigin
	@ApiOperation(value = "The Order Details Update Web Service Endpoint", notes = "This web service endpoint returns the Order detials or success message with json format or xml format when the order is updated")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@PutMapping(path="/{id}", 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) {
		
		logger.info("OrderController -> updateOrder(id, order) method has been called");
		OrderDetailsResponse returnValue = new OrderDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		OrderDto orderDto = new OrderDto();
		orderDto = modelMapper.map(order, OrderDto.class);
		
		OrderDto updatedOrder = orderService.updateOrderDetails(id, orderDto);
		returnValue = modelMapper.map(updatedOrder, OrderDetailsResponse.class);
		
		logger.info("Return the order to the requester");
		return returnValue;
	}
	
	@CrossOrigin
	@ApiOperation(value = "The Order Deletion Web Service Endpoint", notes = "This web service endpoint returns success token with json format or xml format when the order is deleted")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@DeleteMapping(path = "/{id}", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteOrder(@PathVariable String id) {
		
		logger.info("OrderController -> deleteOrder(id) method has been called");
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		orderService.deleteOrder(id);
		
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		logger.info("Return the message to the requester");
		return returnValue;
	}
	
	@CrossOrigin
	@ApiOperation(value = "The Get Orders Web Service Endpoint", notes = "This web service endpoint returns List of Orders detials with json format or xml format")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@GetMapping(
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<OrderDetailsResponse> getOrders() {
		
		logger.info("OrderController -> getOrders() method has been called");
		List<OrderDetailsResponse> returnValue = new ArrayList<>();
		
		List<OrderDto> orders = orderService.getOrders();
		
		for(OrderDto orderDto : orders) {
			OrderDetailsResponse response = new OrderDetailsResponse();
			BeanUtils.copyProperties(orderDto, response);
			returnValue.add(response);
		}
		
		logger.info("Return the orders' list to the requester");
		return returnValue;
	}
}
