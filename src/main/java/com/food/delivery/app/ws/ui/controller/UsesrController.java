package com.food.delivery.app.ws.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UsesrController {

	@GetMapping
	public String getUser() {
		return "get user method is called";
	}
	
	@PostMapping
	public String createUser() {
		return "create user method is called";
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
