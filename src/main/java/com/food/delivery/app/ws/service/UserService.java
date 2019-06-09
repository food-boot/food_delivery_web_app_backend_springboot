package com.food.delivery.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.food.delivery.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto user);
	UserDto getUser(String email);
}
