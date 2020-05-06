package com.arosseto.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.arosseto.springbootmongodb.dto.AuthorDTO;
import com.arosseto.springbootmongodb.dto.CommentDTO;
import com.arosseto.springbootmongodb.model.Post;
import com.arosseto.springbootmongodb.model.User;
import com.arosseto.springbootmongodb.repository.PostRepository;
import com.arosseto.springbootmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("01/03/2020"), "Let's travel!", "Vou para Vitória no ES", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("21/05/2020"), "Follow me on LinkedIn", "A new post has arrived on LinkedIn", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Have a nice trip, Bro!", sdf.parse("02/03/2020"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("It is really great!", sdf.parse("02/03/2020"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Enjoy!", sdf.parse("03/03/2020"), new AuthorDTO(bob));
		
		post1.getComments().addAll(Arrays.asList(c1, c3));
		post2.getComments().addAll(Arrays.asList(c2));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
