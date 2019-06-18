package com.food.delivery.app.ws.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {

	@GetMapping
	public String getFood() {
		return "get food method is called";
	}

	@PostMapping
	public String createFood() {
		return "create food method is called";
	}

	@PutMapping
	public String updateFood() {
		return "update food method is called";
	}

	@DeleteMapping
	public String deleteFood() {
		return "delete food method is called";
	}
}
