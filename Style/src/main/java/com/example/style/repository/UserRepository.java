package com.example.style.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.style.entity.User;



public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    
}