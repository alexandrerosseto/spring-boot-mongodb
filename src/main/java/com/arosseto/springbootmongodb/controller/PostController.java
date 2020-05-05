package com.arosseto.springbootmongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arosseto.springbootmongodb.controller.util.URL;
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
	
	@ApiOperation(value="Find a post based on user ID")
	@GetMapping(value="/{id}")
	public ResponseEntity<Post> findById(@PathVariable("id") String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value="Find a post based on a text")
	@GetMapping(value="/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByText(text);
		return ResponseEntity.ok().body(list);
	}
}