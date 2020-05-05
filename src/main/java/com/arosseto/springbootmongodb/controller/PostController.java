package com.arosseto.springbootmongodb.controller;

import java.util.Date;
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
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="title", defaultValue="") String title) {
		title = URL.decodeParam(title);
		List<Post> list = service.findByTitle(title);
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value="Find posts based on range of dates and Post or Comment")
	@GetMapping(value="/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate, 
			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
}