package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.User;
import com.example.demo.repositories.UserRepository;

@Configuration
public class DbInstantiation implements CommandLineRunner {

	@Autowired
	UserRepository userRepo;

	@Override
	public void run(String... args) throws Exception {

		userRepo.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		List<User> users = Arrays.asList(maria, alex, bob);
		userRepo.saveAll(users);

	}

}
