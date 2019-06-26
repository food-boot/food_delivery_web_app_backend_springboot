package com.food.delivery.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
		orderEntity.setStatus(false);
		OrderEntity storedOrder = orderRepository.save(orderEntity);
		
		OrderDto returnValue = modelMapper.map(storedOrder, OrderDto.class);
		return returnValue;
	}

	@Override
	public OrderDto getOrderById(String orderId) throws UsernameNotFoundException {
		
		OrderDto returnValue = new OrderDto();
		ModelMapper modelMapper = new ModelMapper();
		
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		
		if(orderEntity == null) throw new UsernameNotFoundException(orderId);
		
		returnValue = modelMapper.map(orderEntity, OrderDto.class);
		
		return returnValue;
	}

	@Override
	public OrderDto updateOrderDetails(String orderId, OrderDto order) {
		
		OrderDto returnValue = new OrderDto();
		ModelMapper modelMapper = new ModelMapper();
		
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		if(orderEntity == null) throw new UsernameNotFoundException(orderId);
		
		orderEntity.setCost(order.getCost());
		orderEntity.setItems(order.getItems());
		orderEntity.setStatus(true);
		
		OrderEntity updatedOrder = orderRepository.save(orderEntity);
		returnValue = modelMapper.map(updatedOrder, OrderDto.class);
		
		return returnValue;
	}

	@Override
	public void deleteOrder(String orderId) {
		
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		if(orderEntity == null) throw new UsernameNotFoundException(orderId);
		
		orderRepository.delete(orderEntity);
		
	}

	@Override
	public List<OrderDto> getOrders() {
		
		List<OrderDto> returnValue = new ArrayList<>();
		
		Iterable<OrderEntity> iteratableObjects = orderRepository.findAll();
		
		for(OrderEntity orderEntity : iteratableObjects) {
			OrderDto orderDto = new OrderDto();
			BeanUtils.copyProperties(orderEntity, orderDto);
			returnValue.add(orderDto);
		}
		return returnValue;
	}

}
