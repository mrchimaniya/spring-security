package com.divergentsl.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.divergentsl.model.User;

@Component
public interface UserRepo extends JpaRepository<User,Integer> {

	Optional<User> findByUsername(String uname);
  
}
