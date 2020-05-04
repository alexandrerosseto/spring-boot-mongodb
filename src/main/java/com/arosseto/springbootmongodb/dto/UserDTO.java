package com.arosseto.springbootmongodb.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.arosseto.springbootmongodb.domain.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Name required")
	@Size(max = 100, message = "The name must contain a maximum of 100 characters.")
	private String id;
	
	@NotBlank(message = "Name required")
	@Size(max = 100, message = "The name must contain a maximum of 100 characters.")
	private String name;
	
	@NotBlank(message = "Name required")
	@Size(max = 100, message = "The name must contain a maximum of 100 characters.")
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
}
