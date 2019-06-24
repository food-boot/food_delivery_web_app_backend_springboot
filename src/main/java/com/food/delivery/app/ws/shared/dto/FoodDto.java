package com.food.delivery.app.ws.shared.dto;

import java.io.Serializable;

public class FoodDto implements Serializable {

	private static final long serialVersionUID = 5323277388000239262L;
	private long id;
	private String foodId;
	private String foodName;
	private String foodCategory;
	private float foodPrice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}

	public float getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(float foodPrice) {
		this.foodPrice = foodPrice;
	}

}
