package com.hr.demo.security.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.demo.security.User.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("")
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(userService.findAll());
	}
	
	@GetMapping("/id")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ResponseEntity.ok(userService.findById(id));
	}
}
