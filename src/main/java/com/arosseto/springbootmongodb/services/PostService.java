package com.arosseto.springbootmongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arosseto.springbootmongodb.model.Post;
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
	
	public List<Post> findByTitle(String title) {
		//return repo.findByTitleContainingIgnoreCase(title);
		return repo.searchTitle(title);
	}
	
	 public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		 maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		 return repo.fullSearch(text, minDate, maxDate);
	 }
}
