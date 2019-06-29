package com.food.delivery.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.food.delivery.app.ws.model.request.UserDetailsRequestModel;
import com.food.delivery.app.ws.model.response.OperationStatusModel;
import com.food.delivery.app.ws.model.response.RequestOperationName;
import com.food.delivery.app.ws.model.response.RequestOperationStatus;
import com.food.delivery.app.ws.model.response.UserRest;
import com.food.delivery.app.ws.service.UserService;
import com.food.delivery.app.ws.shared.dto.UserDto;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	/**
	 * Initialize Logger object
	 */
	org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

	@CrossOrigin
	@ApiOperation(value = "The Get User Details Web Service Endpoint", notes = "This web service endpoint returns the User detials with json format or xml format")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@GetMapping(path = "/{id}", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String id) {

		logger.info("UserController -> getUser(id) method has been called");
		UserRest returnValue = new UserRest();

		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);

		logger.info("Return the user to the requester");
		return returnValue;
	}

	@CrossOrigin
	@ApiOperation(value = "The User Registration Web Service Endpoint", notes = "This web service endpoint returns the User detials with json format or xml format when the user is created")
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

		logger.info("UserController -> createUser(userDetails) method has been called");
		UserRest returnValue = new UserRest();

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto createUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createUser, returnValue);

		logger.info("Return the user to the requester");
		return returnValue;
	}

	@CrossOrigin
	@ApiOperation(value = "The User Details Update Web Service Endpoint", notes = "This web service endpoint returns the User detials or success message with json format or xml format when the user is updated")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@PutMapping(path = "/{id}", 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
		
		logger.info("UserController -> updateUser(id, userDetails) method has been called");
		UserRest returnValue = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto updateUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updateUser, returnValue);
		
		logger.info("Return the user to the requester");
		return returnValue;
	}

	@CrossOrigin
	@ApiOperation(value = "The User Deletion Web Service Endpoint", notes = "This web service endpoint returns success token with json format or xml format when the user is deleted")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@DeleteMapping(path = "/{id}", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id) {
		
		logger.info("UserController -> deleteUser(id) method has been called");
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		userService.deleteUser(id);
		
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		logger.info("Return the message to the requester");
		return returnValue;
	}
	
	@CrossOrigin
	@ApiOperation(value = "The Get Users Web Service Endpoint", notes = "This web service endpoint returns List of User detials with json format or xml format")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authorization", value = "Bearer JWT Token", paramType = "header")})
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserRest> getUsers(){
		
		logger.info("UserController -> getUsers() method has been called");
		List<UserRest> returnValue = new ArrayList<>();
		
		List<UserDto> users = userService.getUsers();
		
		for(UserDto userDto : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}
		
		logger.info("Return the users' list to the requester");
		return returnValue;
	}
	
}
