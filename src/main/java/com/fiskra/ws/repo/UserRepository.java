package com.fiskra.ws.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiskra.ws.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByName(String name);
	
	public User findByEmail(String email);
}
