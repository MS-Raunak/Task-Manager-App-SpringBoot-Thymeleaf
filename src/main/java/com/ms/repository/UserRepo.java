package com.ms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.entity.User;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	Boolean existsByEmail(String email);
	Optional<User> findByUsernameOrEmail(String username, String email);
}
