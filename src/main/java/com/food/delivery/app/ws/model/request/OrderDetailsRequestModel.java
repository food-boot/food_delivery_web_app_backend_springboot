package com.food.delivery.app.ws.model.request;

public class OrderDetailsRequestModel {

	private String[] items;
	private float cost;

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}
