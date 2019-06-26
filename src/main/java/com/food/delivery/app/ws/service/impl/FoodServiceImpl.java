package com.food.delivery.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.food.delivery.app.ws.io.entity.FoodEntity;
import com.food.delivery.app.ws.io.repository.FoodRepository;
import com.food.delivery.app.ws.service.FoodService;
import com.food.delivery.app.ws.shared.Utils;
import com.food.delivery.app.ws.shared.dto.FoodDto;

@Service
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

	@Override
	public FoodDto getFoodById(String foodId) throws UsernameNotFoundException{
		
		FoodDto returnValue = new FoodDto();
		ModelMapper modelMapper = new ModelMapper();
		
		FoodEntity foodEntity = foodRepository.findByFoodId(foodId);

		if(foodEntity == null) throw new UsernameNotFoundException(foodId);
		
		returnValue = modelMapper.map(foodEntity, FoodDto.class);
		return returnValue;
	}

	@Override
	public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws UsernameNotFoundException {
		
		FoodDto returnValue = new FoodDto();
		ModelMapper modelMapper = new ModelMapper();
		
		FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
		
		if(foodEntity == null) throw new UsernameNotFoundException(foodId);
		
		foodEntity.setFoodCategory(foodDetails.getFoodCategory());
		foodEntity.setFoodName(foodDetails.getFoodName());
		foodEntity.setFoodPrice(foodDetails.getFoodPrice());
		
		FoodEntity updatedFoodEntity = foodRepository.save(foodEntity);
		returnValue = modelMapper.map(updatedFoodEntity, FoodDto.class);
		
		return returnValue;
	}

	@Override
	public void deleteFoodItem(String id) throws UsernameNotFoundException {
		
		FoodEntity foodEntity = foodRepository.findByFoodId(id);
		if(foodEntity == null) throw new UsernameNotFoundException(id);
		
		foodRepository.delete(foodEntity);
		
	}

	@Override
	public List<FoodDto> getFoods() {
		
		List<FoodDto> returnValue = new ArrayList<>();
		Iterable<FoodEntity> iterableObjects = foodRepository.findAll();
		
		for(FoodEntity foodEntity: iterableObjects) {
			FoodDto foodDto = new FoodDto();
			BeanUtils.copyProperties(foodEntity, foodDto);
			returnValue.add(foodDto);
		}
		return returnValue;
	}

}
