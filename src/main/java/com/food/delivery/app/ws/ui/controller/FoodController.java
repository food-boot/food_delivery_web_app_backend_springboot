package com.food.delivery.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/foods")
public class FoodController {
	
	@Autowired
	FoodService foodService;

	/**
	 * Initialize Logger object
	 */
	org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@CrossOrigin
	@ApiOperation(value = "The Get Food Details Web Service Endpoint", notes = "This web service endpoint returns the Food detials with json format or xml format")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@GetMapping(path="/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FoodDetailsResponse getFood(@PathVariable String id) {
		
		logger.info("FoodController -> getFood(id) method has been called");
		FoodDetailsResponse returnValue = new FoodDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		FoodDto foodDto = foodService.getFoodById(id);
		returnValue = modelMapper.map(foodDto, FoodDetailsResponse.class);
		
		logger.info("Return the food to the requester");
		return returnValue;
	}

	@CrossOrigin
	@ApiOperation(value = "The Create Food Web Service Endpoint", notes = "This web service endpoint returns the Food detials with json format or xml format when the food is created")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		
		logger.info("FoodController -> createFood(foodDetails) method has been called");
		FoodDetailsResponse returnValue = new FoodDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		FoodDto foodDto = modelMapper.map(foodDetails, FoodDto.class);
		FoodDto createFood = foodService.createFood(foodDto);
		
		returnValue = modelMapper.map(createFood, FoodDetailsResponse.class);
		
		logger.info("Return the food to the requester");
		return returnValue;
	}

	@CrossOrigin
	@ApiOperation(value = "The Food Details Update Web Service Endpoint", notes = "This web service endpoint returns the Food detials or success message with json format or xml format when the food is updated")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@PutMapping(path="/{id}", 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) {
		
		logger.info("FoodController -> updateFood(id, foodDetails) method has been called");
		FoodDetailsResponse returnValue = new FoodDetailsResponse();
		ModelMapper modelMapper = new ModelMapper();
		
		FoodDto foodDto = new FoodDto();
		foodDto = modelMapper.map(foodDetails, FoodDto.class);
		
		FoodDto updatedUser = foodService.updateFoodDetails(id, foodDto);
		returnValue = modelMapper.map(updatedUser, FoodDetailsResponse.class);
		
		logger.info("Return the food to the requester");
		return returnValue;
	}

	@CrossOrigin
	@ApiOperation(value = "The Food Deletion Web Service Endpoint", notes = "This web service endpoint returns success token with json format or xml format when the food is deleted")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@DeleteMapping(path = "/{id}", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteFood(@PathVariable String id) {
		
		logger.info("FoodController -> deleteFood(id) method has been called");
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		foodService.deleteFoodItem(id);
		
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		logger.info("Return the message to the requester");
		return returnValue;
	}
	
	@CrossOrigin
	@ApiOperation(value = "The Get Foods Web Service Endpoint", notes = "This web service endpoint returns List of Foods detials with json format or xml format")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@GetMapping(
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<FoodDetailsResponse> getFoods() {
		
		logger.info("FoodController -> getFoods() method has been called");
		List<FoodDetailsResponse> returnValue = new ArrayList<>();
		
		List<FoodDto> foods = foodService.getFoods();
		
		for(FoodDto foodDto: foods) {
			FoodDetailsResponse response = new FoodDetailsResponse();
			BeanUtils.copyProperties(foodDto, response);
			returnValue.add(response);
		}
		
		logger.info("Return the foods' list to the requester");
		return returnValue;
	}
}
