package com.food.delivery.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "foods")
public class FoodEntity implements Serializable {

	private static final long serialVersionUID = 767958036359069705L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String foodId;
	
	@Column(nullable = false)
	private String foodName;
	
	@Column(nullable = false)
	private float foodPrice;
	
	@Column(nullable = false)
	private String foodCategory;

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

	public float getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(float foodPrice) {
		this.foodPrice = foodPrice;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}

}
