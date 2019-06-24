package com.food.delivery.app.ws.ui.controller;

import org.modelmapper.ModelMapper;
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

import com.food.delivery.app.ws.model.request.FoodDetailsRequestModel;
import com.food.delivery.app.ws.model.response.FoodDetailsResponse;
import com.food.delivery.app.ws.model.response.OperationStatusModel;
import com.food.delivery.app.ws.model.response.RequestOperationName;
import com.food.delivery.app.ws.model.response.RequestOperationStatus;
import com.food.delivery.app.ws.service.FoodService;
import com.food.delivery.app.ws.shared.dto.FoodDto;

@RestController
@RequestMapping("/foods")
public class FoodController {
	
	@Autowired
	FoodService foodService;

	@CrossOrigin
	@GetMapping(path="/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FoodDetailsResponse getFood(@PathVariable String id) {
		
		FoodDetailsResponse returnValue = new FoodDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		FoodDto foodDto = foodService.getFoodById(id);
		returnValue = modelMapper.map(foodDto, FoodDetailsResponse.class);
		return returnValue;
	}

	@CrossOrigin
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

	@CrossOrigin
	@PutMapping(path="/{id}", 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) {
		
		FoodDetailsResponse returnValue = new FoodDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		FoodDto foodDto = new FoodDto();
		foodDto = modelMapper.map(foodDetails, FoodDto.class);
		
		FoodDto updatedUser = foodService.updateFoodDetails(id, foodDto);
		returnValue = modelMapper.map(updatedUser, FoodDetailsResponse.class);
		return returnValue;
	}

	@CrossOrigin
	@DeleteMapping(path = "/{id}", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteFood(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		foodService.deleteFoodItem(id);
		
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}
}
