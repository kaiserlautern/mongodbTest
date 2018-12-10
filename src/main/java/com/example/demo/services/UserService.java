package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;


@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public User findById(String id) {
		Optional<User> optional = repo.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("objeto não encontrado"));
	}

	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newUser = repo.findById(obj.getId()).get();
		updateData(newUser, obj);
		return repo.save(newUser);
	}
	
	private void updateData(User newUser, User obj) {
		newUser.setNome(obj.getNome());
		newUser.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(),userDTO.getNome(), userDTO.getEmail());
	}
}
