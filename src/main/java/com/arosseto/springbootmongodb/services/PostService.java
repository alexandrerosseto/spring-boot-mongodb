package com.arosseto.springbootmongodb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arosseto.springbootmongodb.domain.Post;
import com.arosseto.springbootmongodb.repository.PostRepository;
import com.arosseto.springbootmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("No record found"));
	}
}
