package com.food.delivery.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "orders")
public class OrderEntity implements Serializable {

	private static final long serialVersionUID = -6946120796474180178L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String orderId;

	@Column(nullable = false)
	private float cost;

	@Column(nullable = false)
	private String[] items;

	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private boolean status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
