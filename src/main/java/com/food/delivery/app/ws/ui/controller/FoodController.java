package com.food.delivery.app.ws.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.delivery.app.ws.model.request.FoodDetailsRequestModel;
import com.food.delivery.app.ws.model.response.FoodDetailsResponse;
import com.food.delivery.app.ws.service.FoodService;
import com.food.delivery.app.ws.shared.dto.FoodDto;

@RestController
@RequestMapping("/foods")
public class FoodController {
	
	@Autowired
	FoodService foodService;

	@GetMapping
	public String getFood() {
		return "get food method is called";
	}

	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		
		FoodDetailsResponse returnValue = new FoodDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		FoodDto foodDto = modelMapper.map(foodDetails, FoodDto.class);
		FoodDto createFood = foodService.createFood(foodDto);
		
		returnValue = modelMapper.map(createFood, FoodDetailsResponse.class);
		return returnValue;
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
