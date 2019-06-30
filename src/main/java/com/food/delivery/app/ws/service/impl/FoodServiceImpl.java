package com.food.delivery.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
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

	/**
	 * Logger initialization
	 */
	org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * Create food method
	 */
	@Override
	public FoodDto createFood(FoodDto food) {

		logger.info("FoodService -> createFood(food) method is called.");

		ModelMapper modelMapper = new ModelMapper();
		FoodEntity foodEntity = modelMapper.map(food, FoodEntity.class);

		logger.info("Creating new food");

		String publicFoodId = utils.generateFoodId(30);
		foodEntity.setFoodId(publicFoodId);

		logger.info("Saving the food to the database -> foods");

		FoodEntity storedFood = foodRepository.save(foodEntity);

		FoodDto foodDto = new FoodDto();
		foodDto = modelMapper.map(storedFood, FoodDto.class);

		logger.info("Returning the food details to the FoodController via FoodDto object");

		return foodDto;
	}

	/**
	 * Get food by food id method
	 */
	@Override
	public FoodDto getFoodById(String foodId) throws UsernameNotFoundException {

		logger.info("FoodService -> getFoodById(foodId) method is called. Finding the food");

		FoodDto returnValue = new FoodDto();
		ModelMapper modelMapper = new ModelMapper();

		FoodEntity foodEntity = foodRepository.findByFoodId(foodId);

		if (foodEntity == null) {

			logger.error("Can't find the food id from the database");

			throw new UsernameNotFoundException(foodId);
		}

		returnValue = modelMapper.map(foodEntity, FoodDto.class);

		logger.info("Food details found. Returning the food to the FoodController via FoodDto object");

		return returnValue;
	}

	/**
	 * Update food method
	 */
	@Override
	public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws UsernameNotFoundException {

		logger.info("FoodService -> updateFoodDetails(foodId, foodDetails) method is called. Finding the food");

		FoodDto returnValue = new FoodDto();
		ModelMapper modelMapper = new ModelMapper();

		FoodEntity foodEntity = foodRepository.findByFoodId(foodId);

		if (foodEntity == null) {
			
			logger.error("Can't find the food id from the database");
			
			throw new UsernameNotFoundException(foodId);
		}

		logger.info("Food details found. Updating food details");
		
		foodEntity.setFoodCategory(foodDetails.getFoodCategory());
		foodEntity.setFoodName(foodDetails.getFoodName());
		foodEntity.setFoodPrice(foodDetails.getFoodPrice());

		logger.info("Saving updated details to the database -> foods");
		
		FoodEntity updatedFoodEntity = foodRepository.save(foodEntity);
		returnValue = modelMapper.map(updatedFoodEntity, FoodDto.class);

		logger.info("Returning the food to the FoodController via FoodDto object");
		
		return returnValue;
	}

	/**
	 * Delete use method
	 */
	@Override
	public void deleteFoodItem(String id) throws UsernameNotFoundException {

		logger.info("FoodService -> deleteFoodItem(foodId) method is called. Finding the food");
		
		FoodEntity foodEntity = foodRepository.findByFoodId(id);
		
		if (foodEntity == null) {
			
			logger.error("Can't find the food id from the database");
			
			throw new UsernameNotFoundException(id);}

		logger.info("Food details found. Deleting the food from the database -> foods");
		
		foodRepository.delete(foodEntity);

	}

	@Override
	public List<FoodDto> getFoods() {

		logger.info("FoodService -> getFoods() method is called. Collecting all food items details");
		
		List<FoodDto> returnValue = new ArrayList<>();
		Iterable<FoodEntity> iterableObjects = foodRepository.findAll();

		for (FoodEntity foodEntity : iterableObjects) {
			FoodDto foodDto = new FoodDto();
			BeanUtils.copyProperties(foodEntity, foodDto);
			returnValue.add(foodDto);
		}
		
		logger.info("Foods' details found. Returning foods to the FoodController via List<FoodDto>");
		
		return returnValue;
	}

}
