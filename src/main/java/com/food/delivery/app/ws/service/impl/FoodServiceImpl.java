package com.food.delivery.app.ws.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.food.delivery.app.ws.io.entity.FoodEntity;
import com.food.delivery.app.ws.io.repository.FoodRepository;
import com.food.delivery.app.ws.service.FoodService;
import com.food.delivery.app.ws.shared.Utils;
import com.food.delivery.app.ws.shared.dto.FoodDto;

public class FoodServiceImpl implements FoodService {

	@Autowired
	FoodRepository foodRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public FoodDto createFood(FoodDto food) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		FoodEntity foodEntity = modelMapper.map(food, FoodEntity.class);
		
		String publicFoodId = utils.generateFoodId(30);
		foodEntity.setFoodId(publicFoodId);
		
		FoodEntity storedFood = foodRepository.save(foodEntity);
		
		FoodDto foodDto = new FoodDto();
		foodDto = modelMapper.map(storedFood, FoodDto.class);
		return foodDto;
	}

}
