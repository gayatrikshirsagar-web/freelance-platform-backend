package com.freelance_platform.repository;

import com.freelance_platform.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Integer> {
	 Optional<User> findByEmail(String email);
}