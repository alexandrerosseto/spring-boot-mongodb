package com.arosseto.springbootmongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arosseto.springbootmongodb.domain.Post;
import com.arosseto.springbootmongodb.services.PostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/posts")
@CrossOrigin(origins="*")
@Api(value="/posts",  tags="Post")
public class PostController {
	
	@Autowired
	private PostService service;

	
	@ApiOperation(value="Find user by ID")
	@GetMapping(value="/{id}")
	public ResponseEntity<Post> findById(@PathVariable("id") String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}