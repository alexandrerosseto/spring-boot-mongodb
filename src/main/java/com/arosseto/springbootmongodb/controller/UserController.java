package com.arosseto.springbootmongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arosseto.springbootmongodb.domain.User;
import com.arosseto.springbootmongodb.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/users")
@CrossOrigin(origins="*")
@Api(value="/users",  tags="User")
public class UserController {
	
	@Autowired
	private UserService service;

	@ApiOperation(value="Find all users from a list")
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
