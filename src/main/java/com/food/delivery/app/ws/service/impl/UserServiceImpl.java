package com.food.delivery.app.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.delivery.app.ws.io.entity.UserEntity;
import com.food.delivery.app.ws.io.repository.UserRepository;
import com.food.delivery.app.ws.service.UserService;
import com.food.delivery.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		if(userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exists!");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setPicURL("temporory_pic_url");
		userEntity.setEncryptedPassword("sample_Password");
		userEntity.setUserId("sample_user_id");
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}

}
	