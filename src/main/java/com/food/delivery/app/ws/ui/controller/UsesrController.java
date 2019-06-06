package com.food.delivery.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.delivery.app.ws.model.request.UserDetailsRequestModel;
import com.food.delivery.app.ws.model.response.UserRest;
import com.food.delivery.app.ws.service.UserService;
import com.food.delivery.app.ws.shared.dto.UserDto;

@RestController
@RequestMapping("users")
public class UsesrController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUser() {
		return "get user method is called";
	}
	
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		
		UserRest returnValue  = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createUser, returnValue);
		
		return returnValue;
	}
	
	@PutMapping
	public String updateUser() {
		return "update user method is called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete user method is called";
	}
}
