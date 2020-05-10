package com.arosseto.springbootmongodb.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arosseto.springbootmongodb.dto.UserDTO;
import com.arosseto.springbootmongodb.model.Post;
import com.arosseto.springbootmongodb.model.User;
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

	// Although the use of DTO would not be required, it was used for demonstration purpose only. A DTO could be well applied here if there was some sensitive data or for performance endings. 
	@ApiOperation(value="Find all users from a list")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDto = new UserDTO().toList(list);
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value="Find user by ID")
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable("id") String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@ApiOperation(value="Insert new user")
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = objDto.fromDTO(objDto);
		obj = service.insert(obj);
		
		/* Return the memory position of a new created object */
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value="Delete user by ID")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Update an user")
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable("id") String id) {
		User obj = objDto.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Find user's posts using user ID")
	@GetMapping(value="/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable("id") String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
}