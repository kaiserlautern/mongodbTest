package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = service.findAll();
		List<UserDTO> usersDto = users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usersDto);
	}
	
	@RequestMapping(method=RequestMethod.GET,value= "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		UserDTO userDto = new UserDTO(service.findById(id));
		return ResponseEntity.ok().body(userDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UserDTO> insert(@PathVariable String id){
		UserDTO userDto = new UserDTO(service.findById(id));
		return ResponseEntity.ok().body(userDto);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
