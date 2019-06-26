package com.food.delivery.app.ws.service;

import com.food.delivery.app.ws.shared.dto.OrderDto;

public interface OrderService {

	OrderDto createOrder(OrderDto order);
	OrderDto getOrderById(String orderId);
	OrderDto updateOrderDetails(String orderId, OrderDto order);
	void deleteOrder(String orderId);
}
