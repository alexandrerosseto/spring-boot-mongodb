package com.arosseto.springbootmongodb.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.arosseto.springbootmongodb.controller.util.URL;
import com.arosseto.springbootmongodb.model.User;
import com.arosseto.springbootmongodb.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    ObjectMapper mapper;
    
    User maria;
    User alex;
    String requestUri = "/users";
    List<User> userList = new ArrayList<>();
    
    @Before
    public void setup() throws Exception {
		maria = new User("1", "Maria Brown", "maria@gmail.com");
		alex = new User("2", "Alex Green", "alex@gmail.com");
		userList.add(maria);
		userList.add(alex);
    }
    
	protected MockMvc getMockMvc() {
		return this.mvc;
	}
	
    @Test
    public void findAllReturnAllUsers() throws Exception {
    	//given
    	Mockito.when(userService.findAll()).thenReturn(userList);
    	String response = URL.asJsonString(userList);

    	// when
    	getMockMvc().perform(
    		get(requestUri)
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(URL.asJsonString(response)))
	    	.andDo(print())
	    	.andExpect(MockMvcResultMatchers.status().isOk());    	

    	// then
    	Mockito.verify(userService).findAll();
    }
    
    @Test
    public void findUserReturnsUser() throws Exception {
    	// given
    	Mockito.when(userService.findById("1")).thenReturn(maria);
    	
    	// when
    	getMockMvc().perform(get(requestUri + "/{id}", 1))
	    	.andExpect(status().isOk());
    	
    	// then
    	Mockito.verify(userService).findById("1");
    }
    
    @Test
    public void deleteUserReturnsNothing() throws Exception {
    	// given
    	Mockito.doNothing().when(userService).delete("1");
    	
    	//when
    	getMockMvc().perform(delete(requestUri + "/{id}", 1))
    		.andExpect(status().isNoContent());
    	
    	// then
    	Mockito.verify(userService).delete("1");
    }
    
    @Test
    public void updateUserReturnsUser() throws Exception {
    	// given
    	User jose = new User("1", "Jose", "jose@gmail.com");
    	Mockito.when(userService.findById("1")).thenReturn(maria);
    	Mockito.when(userService.update(jose)).thenReturn(jose);
    	
    	// when
        getMockMvc().perform(
                put(requestUri + "/{id}", jose.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(URL.asJsonString(jose)))
                .andExpect(status().isNoContent());

    	// then
        Mockito.verify(userService, Mockito.times(1)).update(jose);
    }
    
    @Test
    public void insertUserReturnsUser() throws Exception {
    	// given    	
    	User jose = new User("3", "Jose", "jose@gmail.com");
    	Mockito.when(userService.exists(jose.getId())).thenReturn(false);
    	Mockito.when(userService.insert(jose)).thenReturn(jose);
    	
    	// when
        getMockMvc().perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(URL.asJsonString(jose)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString(requestUri)));
        
        // then 
        Mockito.verify(userService, Mockito.times(1)).insert(jose);
        Mockito.verifyNoMoreInteractions(userService);
    }
}