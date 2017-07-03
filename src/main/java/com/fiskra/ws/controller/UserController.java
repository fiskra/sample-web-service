package com.fiskra.ws.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiskra.ws.model.User;
import com.fiskra.ws.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	// or below
	/**
	 * private final UserRepository userRepository;
	 * 
	 * @Autowired
	 * public UserController(UserRepository userRepository){
	 * 	  this.userRepository = userRepository;
	 * }
	 * 
	 * 
	 */
	
	@RequestMapping(method= RequestMethod.GET)
	Collection<User> getAllUsers(){
		return userRepository.findAll();
	}
	

}
