package com.arosseto.springbootmongodb.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.arosseto.springbootmongodb.model.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Id required")
	private String id;
	
	@NotBlank(message = "Name required")
	@Size(max = 100, message = "The name must contain a maximum of 100 characters.")
	private String name;
	
	@NotBlank(message = "E-mail required")
	@Size(max = 100, message = "The e-mail must contain a maximum of 100 characters.")
	private String email;
	
	public UserDTO() {
	}
	
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	public List<UserDTO> toList(List<User> listObj) {
		List<UserDTO> listDto = listObj.stream().map(u -> new UserDTO(u)).collect(Collectors.toList()); 
		return listDto;
	}
}
