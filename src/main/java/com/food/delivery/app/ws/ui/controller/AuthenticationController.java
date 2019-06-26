package com.food.delivery.app.ws.ui.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.food.delivery.app.ws.model.request.LoginRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
public class AuthenticationController {

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
}
