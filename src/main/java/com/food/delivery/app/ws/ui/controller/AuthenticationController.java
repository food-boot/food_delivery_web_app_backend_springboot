package com.food.delivery.app.ws.ui.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.food.delivery.app.ws.model.request.LoginRequest;
import com.food.delivery.app.ws.model.response.UserRest;
import com.food.delivery.app.ws.service.UserService;
import com.food.delivery.app.ws.shared.dto.UserDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
public class AuthenticationController {
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;

	@ApiOperation("User login")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Response Headera", responseHeaders = {
			@ResponseHeader(name = "authorization", description = "Bearer <JWT value here>", response = String.class),
			@ResponseHeader(name = "userId", description = "<Public User Id value here>", response = String.class) 
			}) 
	})
	
	@PostMapping("/users/login")
	public void fakeLogin(@RequestBody LoginRequest login) {
		throw new IllegalStateException("You can't access this method using api documentation!");
	}
	
	@RequestMapping(value = "/users/{email}", method = RequestMethod.GET)
	public UserRest getUserByEmail(@PathVariable String email) {
		
		UserRest returnValue = new UserRest();
		
		UserDto user = userService.getUser(email);
		BeanUtils.copyProperties(user, returnValue);
		
		return returnValue;
	}
}
