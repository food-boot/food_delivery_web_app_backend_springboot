package com.food.delivery.app.ws.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.delivery.app.ws.io.entity.OrderEntity;
import com.food.delivery.app.ws.io.repository.OrderRepository;
import com.food.delivery.app.ws.service.OrderService;
import com.food.delivery.app.ws.shared.Utils;
import com.food.delivery.app.ws.shared.dto.OrderDto;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	Utils utils;

	@Override
	public OrderDto createOrder(OrderDto order) {
		
		ModelMapper modelMapper = new ModelMapper();
		OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
		
		String orderId = utils.generateOrderId(10);
		orderEntity.setOrderId(orderId);
		OrderEntity storedOrder = orderRepository.save(orderEntity);
		
		OrderDto returnValue = modelMapper.map(storedOrder, OrderDto.class);
		return returnValue;
	}

}
